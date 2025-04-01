package org.transportationroutecalculation.prolab2_1_ver2.APİs.DrawRoute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.transportationroutecalculation.prolab2_1_ver2.APİs.RequestData;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.Route2;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathClasses.Path2;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.AlternativePath.AlternativePath;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.AlternativePath.AlternativePathFactory;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.RouteProcess.RouteBuilder;

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
    public void drawRoute(RequestData data, HashMap<String, List<Route2>> routeResult) {

        Map<String, AlternativePath> strategies = alternativePathFactory.getAllStrategies();

        for (Map.Entry<String, AlternativePath> entry : strategies.entrySet()) {
            String alternativePathTypeName = entry.getKey();
            AlternativePath alternativePath = entry.getValue();

            routeResult.putIfAbsent(alternativePathTypeName, new ArrayList<>());
            Path2 calculatedPath = alternativePath.calculatePath(data);

            Route2 alternativeRoute = RouteBuilder.buildFromPath(calculatedPath);

            double remainMoney = data.getPaymentMethod()
                    .map(paymentMethod -> paymentMethod.pay(alternativeRoute.getAmount()))
                    .orElse(0.0);
            alternativeRoute.setRemainMoney(remainMoney);

            routeResult.get(alternativePathTypeName).add(alternativeRoute);
        }
    }
}
