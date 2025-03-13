package org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths;

import org.transportationroutecalculation.prolab2_1_ver2.Graph.Edge;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;

import java.util.List;
import java.util.Map;

public interface ShortestPaths {

    abstract void printGraph(Map<Stations, List<Edge>> graphMap);
}
