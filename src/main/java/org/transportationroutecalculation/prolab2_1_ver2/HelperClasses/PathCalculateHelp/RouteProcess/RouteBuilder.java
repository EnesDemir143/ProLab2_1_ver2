package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.RouteProcess;

import org.springframework.stereotype.Component;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.Route2;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathClasses.Path2;

@Component
public class RouteBuilder {
    public static Route2 buildFromPath(Path2 path) {
        return new Route2(
                path.getPath(),
                path.getDistance(),
                path.getTime(),
                path.getAmount(),
                path.getBest_for(),
                path.getRemainMoney()
        );
    }
}