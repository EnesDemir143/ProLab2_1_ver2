package org.transportationroutecalculation.prolab2_1_ver2.APİs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.PathCalculate;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.Route2;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.AlternativePathType;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathRecords.Path2;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathType;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.AlternativePath.AlternativePath;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.AlternativePath.AlternativePathFactory;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.RouteProcess.RouteBuilder;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.RouteProcess.RouteConcat;
import org.transportationroutecalculation.prolab2_1_ver2.Payment.PaymentMethods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class DrawRoute {

    private final RouteConcat routeConcat;
    private final PathCalculate pathCalculate;
    private AlternativePathFactory alternativePathFactory;

    @Autowired
    public DrawRoute(RouteConcat routeConcat, PathCalculate pathCalculate, AlternativePathFactory alternativePathFactory) {
        this.routeConcat = routeConcat;
        this.pathCalculate = pathCalculate;
        this.alternativePathFactory = alternativePathFactory;
    }

    public HashMap<String, List<Route2>> drawRoute(@RequestBody RequestData data) {
        HashMap<String, List<Route2>> routeResult = new HashMap<>();

        calculateStandardRoutes(data, routeResult);

        addAlternativeRoute(data, routeResult);

        return routeResult;
    }

    private void calculateStandardRoutes(RequestData data, HashMap<String, List<Route2>> routeResult) {
        for (PathType pathType : PathType.values()) {
            String pathTypeName = pathType.name().toLowerCase(Locale.ROOT);
            HashMap<String, List<Route2>> calculatedRoutes = pathCalculate.path_calculate(data, pathTypeName);

            for (Map.Entry<String, List<Route2>> entry : calculatedRoutes.entrySet()) {
                routeResult.putIfAbsent(pathTypeName, new ArrayList<>());

                double remainMoney = data.getPaymentMethod()
                        .map(paymentMethod -> paymentMethod.pay(entry.getValue().getFirst().getAmount()))
                        .orElse(0.0);

                entry.getValue().getFirst().setRemainMoney(remainMoney);

                List<Route2> currentList = routeResult.get(pathTypeName);

                currentList.addAll(entry.getValue());

            }
        }
    }

    private void addAlternativeRoute(RequestData data, HashMap<String, List<Route2>> routeResult) {

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