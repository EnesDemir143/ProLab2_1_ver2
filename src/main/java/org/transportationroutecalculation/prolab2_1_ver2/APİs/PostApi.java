package org.transportationroutecalculation.prolab2_1_ver2.APİs;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RestController;
    import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.*;
    import org.transportationroutecalculation.prolab2_1_ver2.Graph.Graph;
    import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.NearestStations.FindNearestStation;
    import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;

    import java.security.Principal;
    import java.util.Arrays;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;
    import java.util.stream.Collectors;

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

        public static void print2DArray(double[][] array) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    System.out.print("data" + array[i][j] + " ");
                }
                System.out.println(); // Her satırdan sonra yeni bir satır
            }
        }

        public record Route(List<Map<String, Double>> path, double distance, int duration, double price, String bestFor) {}

        @PostMapping("/api/draw_route")
        public ResponseEntity<HashMap <String, List<Route>>> drawRoute(@RequestBody RequestData data, Principal principal) {
            try {
                // Başlangıç ve hedef noktalarını Stations objelerine çevir
                Stations startStation = findNearestStation.find_nearest_station(data.getCurrentLocation()).getFirst().stations();
                System.out.println("Start station: " + startStation.getStationID());
                Stations endStation = findNearestStation.find_nearest_station(data.getTargetLocation()).getFirst().stations();
                System.out.println("End station: " + endStation.getStationID());

                Path path_for_time = aStar.findShortestPaths(startStation, endStation, Metric.TIME);
                print2DArray(path_for_time.path());
                System.out.println("Time: " + path_for_time.time());

                Path path_for_distance = aStar.findShortestPaths(startStation, endStation, Metric.DISTANCE);
                print2DArray(path_for_distance.path());
                System.out.println("Distance: " + path_for_distance.distance());

                Path path_for_amount = aStar.findShortestPaths(startStation, endStation, Metric.AMOUNT);
                print2DArray(path_for_amount.path());
                System.out.println("Amount: " + path_for_amount.amount());

                List<Route> routes = List.of(
                        new Route(convertPathToCoords(path_for_time.path()), path_for_time.distance(), path_for_time.time(), path_for_time.amount(), "Süre (Time)"),
                        new Route(convertPathToCoords(path_for_distance.path()), path_for_distance.distance(), path_for_distance.time(), path_for_distance.amount(), "Mesafe (Distance)"),
                        new Route(convertPathToCoords(path_for_amount.path()), path_for_amount.distance(), path_for_amount.time(), path_for_amount.amount(), "Tutar (Amount)")
                );

                HashMap <String, List<Route>> backEndReturn= new HashMap<>();
                backEndReturn.put("routes", routes);
                return ResponseEntity.ok(backEndReturn);

            } catch (Exception e) {
                System.err.println("Hata oluştu: " + e.getMessage());
                e.printStackTrace();
                return ResponseEntity.status(500).build();
            }
        }

        private List<Map<String, Double>> convertPathToCoords(double[][] path) {
            return Arrays.stream(path)
                    .map(coord -> Map.of("lat", coord[0], "lon", coord[1]))
                    .collect(Collectors.toList());
        }
    }