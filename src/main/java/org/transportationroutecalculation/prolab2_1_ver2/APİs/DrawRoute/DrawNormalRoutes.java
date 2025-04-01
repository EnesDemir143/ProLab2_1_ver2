package org.transportationroutecalculation.prolab2_1_ver2.APİs.DrawRoute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.transportationroutecalculation.prolab2_1_ver2.APİs.RequestData;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.PathCalculate;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.Route2;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathType;

import java.util.*;

@Service
public class DrawNormalRoutes implements DrawRoute {

    private final PathCalculate pathCalculate;

    @Autowired
    public DrawNormalRoutes(PathCalculate pathCalculate) {
        this.pathCalculate = pathCalculate;
    }

    @Override
    public void drawRoute(RequestData data, HashMap<String, List<Route2>> routeResult) {
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

}
