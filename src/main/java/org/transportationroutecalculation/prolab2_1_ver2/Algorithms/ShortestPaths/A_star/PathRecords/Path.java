package org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathRecords;

import java.util.Deque;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public record Path(Deque<Map.Entry<String, double[]>> path , AtomicReference<Double> distance, AtomicReference<Integer> time, AtomicReference<Double> amount,
                   String best_for) {
}
