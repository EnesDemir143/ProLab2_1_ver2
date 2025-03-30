package org.transportationroutecalculation.prolab2_1_ver2.APİs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.PathCalculate;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.Route2;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathType;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.RouteProcess.RouteBuilder;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.RouteProcess.RouteConcat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class DrawRoute {

    private final RouteConcat routeConcat;
    private final PathCalculate pathCalculate;
    private static final String ALTERNATIVE_ROUTE_KEY = "alternative";

    @Autowired
    public DrawRoute(RouteConcat routeConcat, PathCalculate pathCalculate) {
        this.routeConcat = routeConcat;
        this.pathCalculate = pathCalculate;
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

                List<Route2> currentList = routeResult.get(pathTypeName);

                currentList.addAll(entry.getValue());
            }
        }
    }

    private void addAlternativeRoute(RequestData data, HashMap<String, List<Route2>> routeResult) {
        routeResult.putIfAbsent(ALTERNATIVE_ROUTE_KEY, new ArrayList<>());

        Route2 alternativeRoute = RouteBuilder.buildFromPath(routeConcat.calculateAlternativePath(data));
        routeResult.get(ALTERNATIVE_ROUTE_KEY).add(alternativeRoute);
    }
}