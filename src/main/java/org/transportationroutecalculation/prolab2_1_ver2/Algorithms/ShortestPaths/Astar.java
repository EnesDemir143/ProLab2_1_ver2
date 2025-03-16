package org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths;

import org.jetbrains.annotations.NotNull;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;

import java.util.Map;

public class Astar implements ShortestPaths{

    static class Node implements Comparable<Node> {

        int x, y;
        double gcost, hcost, fcost;
        Node parent;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(@NotNull Node o) {
            return 0;
        }
    }

    private double calculateHeuristic(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }


    @Override
    public void findShortestPaths(Stations startStation, Stations endStation) {

    }
}
