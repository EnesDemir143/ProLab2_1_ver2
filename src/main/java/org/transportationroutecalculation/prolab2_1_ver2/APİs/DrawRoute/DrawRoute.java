package org.transportationroutecalculation.prolab2_1_ver2.APİs.DrawRoute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.transportationroutecalculation.prolab2_1_ver2.APİs.RequestData;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.Route2;

import java.util.HashMap;
import java.util.List;

@Service
public class DrawRoute {

    private DrawNormalRoutes drawNormalRoutes;
    private DrawAlternativeRoute drawAlternativeRoute;

    @Autowired
    public DrawRoute(DrawNormalRoutes drawNormalRoutes, DrawAlternativeRoute drawAlternativeRoute) {
        this.drawNormalRoutes = drawNormalRoutes;
        this.drawAlternativeRoute = drawAlternativeRoute;
    }

    public HashMap<String, List<Route2>> drawRoute(RequestData data) {
        HashMap<String, List<Route2>> routeResult = new HashMap<>();

        drawNormalRoutes.calculateStandardRoutes(data, routeResult);

        drawAlternativeRoute.addAlternativeRoute(data, routeResult);

        return routeResult;
    }
}