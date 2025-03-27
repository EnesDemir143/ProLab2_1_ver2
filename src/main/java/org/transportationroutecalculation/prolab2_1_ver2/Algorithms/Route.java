package org.transportationroutecalculation.prolab2_1_ver2.Algorithms;

import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public record Route(Deque<Map.Entry<String, double[]>> path, AtomicReference<Double> distance, AtomicReference<Integer> time, AtomicReference<Double> amount, String bestFor) {}
