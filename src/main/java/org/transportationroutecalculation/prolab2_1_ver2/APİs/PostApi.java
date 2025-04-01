package org.transportationroutecalculation.prolab2_1_ver2.APİs;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RestController;
    import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.Route2;
    import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Passengers.Passengers;
    import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.Controllers.PassengerController;


    import java.security.Principal;
    import java.util.*;


@RestController
    public class PostApi {

        private HashMap<String ,RequestData> requestDataList = new HashMap<>();
        private DrawRoute drawRoute;
        private final PaymentService paymentService;

        @Autowired
        public PostApi(DrawRoute drawRoute, PaymentService paymentService) {
            this.drawRoute = drawRoute;
            this.paymentService = paymentService;
        }


        @PostMapping("/api/draw_route")
        public ResponseEntity<HashMap <String, List<Route2>>> drawRoute(@RequestBody RequestData data, Principal principal) {
            try {

                data = new PassengerController().passengerControl(requestDataList, data);

                requestDataList.put(data.getPassenger().map(Passengers::getNameSurname).orElse(" "), data);

                HashMap<String, List<Route2>> backEndReturn = drawRoute.drawRoute(data);

                return ResponseEntity.ok(backEndReturn);

            } catch (Exception e) {
                System.err.println("Hata oluştu: " + e.getMessage());
                e.printStackTrace();
                return ResponseEntity.status(500).build();
            }
        }

}