package org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths;

import org.transportationroutecalculation.prolab2_1_ver2.Graph.Edge;

import java.util.function.Function;


public enum Metric {
    DISTANCE(Edge::getDistance),
    TIME(edge -> (double) edge.getTime()),
    AMOUNT(Edge::getAmount);
    private final Function<Edge, Double> metricFunction;

    Metric(Function<Edge, Double> metricFunction) {
        this.metricFunction = metricFunction;
    }

    public Function<Edge, Double> getMetricFunction() {
        return metricFunction;
    }

    public Object getMetricName() {
        return switch (this) {
            case DISTANCE -> "Distance";
            case TIME -> "Time";
            case AMOUNT -> "Amount";
        };
    }
}