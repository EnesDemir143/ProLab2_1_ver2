package org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths;

import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;

import java.util.List;

public interface ShortestPaths {

    Path findShortestPaths(Stations startStation, Stations endStation, Metric metric);
}
