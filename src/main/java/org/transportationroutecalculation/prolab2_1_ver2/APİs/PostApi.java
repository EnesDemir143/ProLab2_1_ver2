package org.transportationroutecalculation.prolab2_1_ver2.APİs;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RestController;
    import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.PathCalculate;
    import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.Route;
    import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathType;
    import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.Controllers.PaymentController;
    import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.RouteProcess.RouteBuilder;
    import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.RouteProcess.RouteConcat;
    import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Passengers.Passengers;


    import java.security.Principal;
    import java.util.*;


@RestController
    public class PostApi {

        private final PathCalculate path_calculate;
        private HashMap<String ,RequestData> requestDataList = new HashMap<>();
        private RouteConcat routeConcat;

        @Autowired
        public PostApi(PathCalculate path_calculate, RouteConcat routeConcat) {
            this.path_calculate = path_calculate;
            this.routeConcat = routeConcat;
        }


        @PostMapping("/api/draw_route")
        public ResponseEntity<HashMap <String, List<Route>>> drawRoute(@RequestBody RequestData data, Principal principal) {
            try {

               // data = new PassengetController().passengerControl(requestDataList, data);

                requestDataList.put(data.getPassenger().map(Passengers::getNameSurname).orElse(" "), data);

                HashMap<String, List<Route>> backEndReturn = new HashMap<>();
                for (PathType pathType : PathType.values()) {
                    HashMap<String, List<Route>> calculatedRoutes = path_calculate.path_calculate(data, pathType.name().toLowerCase(Locale.ROOT));

                    for (Map.Entry<String, List<Route>> entry : calculatedRoutes.entrySet()) {
                        backEndReturn.merge(entry.getKey(), entry.getValue(), (existingList, newList) -> {
                            existingList.addAll(newList);
                            return existingList;
                        });
                    }
                }

                backEndReturn.get("routes").add(RouteBuilder.buildFromPath(routeConcat.calculateAlternativePath(data)));

                PaymentController paymentController = new PaymentController(data.getPassenger().orElse(null));
                backEndReturn=paymentController.PayControl(backEndReturn);

                return ResponseEntity.ok(backEndReturn);

            } catch (Exception e) {
                System.err.println("Hata oluştu: " + e.getMessage());
                e.printStackTrace();
                return ResponseEntity.status(500).build();
            }
        }

}