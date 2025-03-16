package org.transportationroutecalculation.prolab2_1_ver2.APİs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.Dijkstra;
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

    @Autowired
    public PostApi(Dijkstra dijkstra, FindNearestStation findNearestStation, Graph graph) {
        this.dijkstra = dijkstra;
        this.findNearestStation = findNearestStation;
        this.graph = graph;
    }

/*    @PostMapping("/api/draw_route")
    public ResponseEntity<Map<String, Object>> drawRoute(@RequestBody RequestData data, Principal principal) {
        try {
            // Başlangıç ve hedef noktalarını Stations objelerine çevir
            Stations startStation = findNearestStation.find_nearest_station(data.getCurrentLocation()).getFirst().stations();
            System.out.println("Start station: " + startStation.getStationID());
            Stations endStation = findNearestStation.find_nearest_station(data.getTargetLocation()).getFirst().stations();

            // Dijkstra algoritmasını çalıştır
            List<Route> routes = dijkstra.findShortestPaths(startStation, endStation);
            System.out.println("Routes: " + routes.size());

            // Mevcut OK yanıtı korundu, sadece routes eklendi
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Rota hesaplama başarıyla alındı");
            response.put("routes", routes); // Dijkstra'dan gelen sonucu ekledik

            Map<String, Object> routeDetails = new HashMap<>();
            routeDetails.put("start_location", data.getCurrentLocation());
            routeDetails.put("target_location", data.getTargetLocation());
            data.getPassenger().ifPresent(passenger -> routeDetails.put("passenger", passenger));
            data.getPaymentMethod().ifPresent(paymentMethod -> routeDetails.put("payment_method", paymentMethod));

            response.put("route_details", routeDetails);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", "İstek işlenirken hata oluştu: " + e.getMessage());
            errorResponse.put("stackTrace", e.getStackTrace()[0].toString());
            e.printStackTrace();
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }*/
}