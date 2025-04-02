package org.transportationroutecalculation.prolab2_1_ver2.APİs.DrawRoute;

import org.transportationroutecalculation.prolab2_1_ver2.APİs.RequestData;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathClasses.Path2;

import java.util.HashMap;
import java.util.List;

public interface DrawRoute {

    public abstract void drawRoute(RequestData data, HashMap<String, List<Path2>> routeResult);
}
