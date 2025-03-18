package org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathRecords;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public record Path(ArrayList<double[]> path , AtomicReference<Double> distance, AtomicReference<Integer> time, AtomicReference<Double> amount, String best_for) {
}
