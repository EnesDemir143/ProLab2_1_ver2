package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.RouteProcess;

import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.Route;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathRecords.Path;

import java.util.concurrent.atomic.AtomicReference;

import org.springframework.stereotype.Component;

@Component
public class RouteBuilder {
    public static Route buildFromPath(Path path) {
        return new Route(
                PathConverter.convertPathToCoords(path.path()),
                new AtomicReference<>(path.distance().get()),
                new AtomicReference<>(path.time().get()),
                new AtomicReference<>(path.amount().get()),
                path.best_for()
        );
    }
}