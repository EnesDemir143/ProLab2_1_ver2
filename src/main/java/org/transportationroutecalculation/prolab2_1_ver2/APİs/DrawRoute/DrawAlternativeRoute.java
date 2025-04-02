package org.transportationroutecalculation.prolab2_1_ver2.APİs.DrawRoute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.transportationroutecalculation.prolab2_1_ver2.APİs.RequestData;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathClasses.Path2;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.AlternativePath.AlternativePath;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.AlternativePath.AlternativePathFactory;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Passengers.Passengers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DrawAlternativeRoute implements DrawRoute{

    private final AlternativePathFactory alternativePathFactory;

    @Autowired
    public DrawAlternativeRoute(AlternativePathFactory alternativePathFactory) {
        this.alternativePathFactory = alternativePathFactory;
    }

    @Override
    public void drawRoute(RequestData data, HashMap<String, List<Path2>> routeResult) {

        Map<String, AlternativePath> strategies = alternativePathFactory.getAllStrategies();

        for (Map.Entry<String, AlternativePath> entry : strategies.entrySet()) {
            String alternativePathTypeName = entry.getKey();
            AlternativePath alternativePath = entry.getValue();

            routeResult.putIfAbsent(alternativePathTypeName, new ArrayList<>());
            Path2 calculatedPath = alternativePath.calculatePath(data);
            double amount = data.getPaymentMethod()
                    .map(paymentMethod -> paymentMethod.calculateAmount(calculatedPath.getAmount(), data.getPassenger().map(Passengers::getDiscountRate).orElse(1.0)))
                    .orElse(calculatedPath.getAmount());
            calculatedPath.setAmount(amount);
            System.out.println("DrawAlternativeRoute: Path type: " + calculatedPath.getBest_for());
            System.out.println("DrawAlternativeRoute: Path amount: " + calculatedPath.getAmount());

            double remainMoney = data.getPaymentMethod()
                    .map(paymentMethod -> paymentMethod.pay(data.getPassenger().orElse(null), calculatedPath.getAmount()))
                    .orElse(0.0);
            calculatedPath.setRemainMoney(remainMoney);

            routeResult.get(alternativePathTypeName).add(calculatedPath);
        }
    }
}
