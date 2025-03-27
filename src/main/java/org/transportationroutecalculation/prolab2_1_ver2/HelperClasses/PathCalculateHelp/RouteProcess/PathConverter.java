package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.RouteProcess;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PathConverter {
    public static Deque<Map.Entry<String, double[]>> convertPathToCoords(Deque<Map.Entry<String, double[]>> path) {
        Deque<Map.Entry<String, double[]>> result = new LinkedList<>();
        path.stream()
                .forEach(coord -> {
                    result.add(Map.entry("lat", new double[]{coord.getValue()[0]}));
                    result.add(Map.entry("lon", new double[]{coord.getValue()[1]}));
                });
        return result;
    }
}