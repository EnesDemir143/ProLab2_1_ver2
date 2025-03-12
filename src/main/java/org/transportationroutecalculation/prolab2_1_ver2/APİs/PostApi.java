package org.transportationroutecalculation.prolab2_1_ver2.APİs;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
public class PostApi {

    private final RouteService routeService;

    @Autowired
    public PostApi(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping("/draw_route")
    public ResponseEntity<String> drawRoute(@RequestBody RequestData data) {
        routeService.setRequestData(data);

        StringBuilder response = new StringBuilder();
        response.append("Route calculation started:\n");
        response.append("Start Location: ").append(data.getCurrentLocation()).append("\n");
        response.append("Target Location: ").append(data.getTargetLocation()).append("\n");

        data.getPassenger().ifPresent(passenger ->
                response.append("Passenger: ").append(passenger).append("\n"));
        data.getPaymentMethod().ifPresent(paymentMethod ->
                response.append("Payment Method: ").append(paymentMethod).append("\n"));

        return ResponseEntity.ok(response.toString());
    }
}