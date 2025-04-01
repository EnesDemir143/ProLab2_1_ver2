package org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths;

import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.Metric;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathClasses.Path2;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;

public interface ShortestPaths {

    Path2 findShortestPaths(Stations startStation, Stations endStation, Metric metric, String type);
}
