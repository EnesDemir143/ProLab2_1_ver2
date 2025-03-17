package org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths;

import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;

import java.util.List;

public interface ShortestPaths {

    List<A_star.Node> findShortestPaths(Stations startStation, Stations endStation);
}
