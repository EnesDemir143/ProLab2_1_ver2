package org.transportationroutecalculation.prolab2_1_ver2.APİs.DrawRoute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.transportationroutecalculation.prolab2_1_ver2.APİs.RequestData;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.PathCalculate;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathClasses.Path2;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathType;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Locations.TargetLocation;

import java.util.*;

@Service
public class DrawNormalRoutes extends TargetLocation implements DrawRoute {

    private final PathCalculate pathCalculate;

    @Autowired
    public DrawNormalRoutes(PathCalculate pathCalculate) {
        this.pathCalculate = pathCalculate;
    }

    @Override
    public void drawRoute(RequestData data, HashMap<String, List<Path2>> routeResult) {
        for (PathType pathType : PathType.values()) {
            String pathTypeName = pathType.name().toLowerCase(Locale.ROOT);
            HashMap<String, List<Path2>> calculatedRoutes = pathCalculate.path_calculate(data, pathTypeName);

            for (Map.Entry<String, List<Path2>> entry : calculatedRoutes.entrySet()) {
                routeResult.putIfAbsent(pathTypeName, new ArrayList<>());

                List<Path2> paths = entry.getValue();
                for (Path2 path : paths) {
                    double remainMoney = data.getPaymentMethod()
                            .map(paymentMethod -> paymentMethod.pay(data.getPassenger().orElse(null), path.getAmount()))
                            .orElse(0.0);

                    path.setRemainMoney(remainMoney);
                }

                List<Path2> currentList = routeResult.get(pathTypeName);
                currentList.addAll(paths);
            }
        }
    }

}
