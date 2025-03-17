package org.transportationroutecalculation.prolab2_1_ver2.APİs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.Dijkstra;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.Node;
import org.transportationroutecalculation.prolab2_1_ver2.Graph.Graph;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.NearestStations.FindNearestStation;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PostApi {

    private final Dijkstra dijkstra; // Dijkstra algoritmasını enjekte ediyoruz
    private final FindNearestStation findNearestStation;
    private final Graph graph;
    private final A_star aStar;

    @Autowired
    public PostApi(Dijkstra dijkstra,A_star aStar , FindNearestStation findNearestStation, Graph graph) {
        this.dijkstra = dijkstra;
        this.aStar = aStar;
        this.findNearestStation = findNearestStation;
        this.graph = graph;
    }

    @PostMapping("/api/draw_route")
    public void drawRoute(@RequestBody RequestData data, Principal principal) {
        try {
            // Başlangıç ve hedef noktalarını Stations objelerine çevir
            Stations startStation = findNearestStation.find_nearest_station(data.getCurrentLocation()).getFirst().stations();
            System.out.println("Start station: " + startStation.getStationID());
            Stations endStation = findNearestStation.find_nearest_station(data.getTargetLocation()).getFirst().stations();
            System.out.println("End station: " + endStation.getStationID());

            // findShortestPaths metodunu çağır ve sonucu debug et
            List<Node> path = aStar.findShortestPaths(startStation, endStation);
            System.out.println("Shortest path: " + path);

            // Path içeriğini detaylı şekilde yazdır
            if (path != null) {
                for (Node node : path) {
                    System.out.println("Node: " + node.station.getStationID()); // Node'un toString() metoduna bağlı
                }
            } else {
                System.out.println("Path is null!");
            }

            // JSON yanıtı döndürmüyoruz, sadece debug yapıyoruz
        } catch (Exception e) {
            System.err.println("Hata oluştu: " + e.getMessage());
            e.printStackTrace();
        }
    }
}