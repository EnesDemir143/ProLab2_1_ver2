package org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star;

import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;

public class Node implements Comparable<Node> {
    Stations station;
    double x, y;
    double gcost, hcost, fcost;
    Node parent;

    Node(Stations station, double x, double y) {
        this.station = station;
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Node o) {
        return Double.compare(this.fcost, o.fcost);
    }
}