package org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathRecords;

public record Path(double[][] path, double distance, int time, double amount, String best_for) {
}
