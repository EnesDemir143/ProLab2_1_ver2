package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.RouteProcess;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PathConverter {
    public static List<Map<String, Double>> convertPathToCoords(List<double[]> path) {
        return path.stream()
                .map(coord -> Map.of("lat", coord[0], "lon", coord[1]))
                .collect(Collectors.toList());
    }
}