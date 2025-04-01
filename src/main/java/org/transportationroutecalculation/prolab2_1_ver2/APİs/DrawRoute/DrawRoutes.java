package org.transportationroutecalculation.prolab2_1_ver2.APİs.DrawRoute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.transportationroutecalculation.prolab2_1_ver2.APİs.RequestData;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.Route2;

import java.util.HashMap;
import java.util.List;

@Service
public class DrawRoutes {

    private DrawNormalRoutes drawNormalRoutes;
    private DrawAlternativeRoute drawAlternativeRoute;

    @Autowired
    public DrawRoutes(DrawNormalRoutes drawNormalRoutes, DrawAlternativeRoute drawAlternativeRoute) {
        this.drawNormalRoutes = drawNormalRoutes;
        this.drawAlternativeRoute = drawAlternativeRoute;
    }

    public HashMap<String, List<Route2>> drawRoute(RequestData data) {
        HashMap<String, List<Route2>> routeResult = new HashMap<>();

        drawNormalRoutes.drawRoute(data, routeResult);
        drawAlternativeRoute.drawRoute(data, routeResult);

        return routeResult;
    }
}