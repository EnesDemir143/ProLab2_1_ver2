package org.transportationroutecalculation.prolab2_1_ver2.Algorithms;

import java.util.List;
import java.util.Map;

public record Route(List<Map<String, Double>> path, double distance, int duration, double price, String bestFor) {}
