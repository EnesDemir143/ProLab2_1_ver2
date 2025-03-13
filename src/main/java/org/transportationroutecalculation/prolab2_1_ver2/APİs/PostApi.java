package org.transportationroutecalculation.prolab2_1_ver2.APİs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PostApi {

    private final RouteService routeService;

    @Autowired
    public PostApi(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping("/api/draw_route")
    public ResponseEntity<Map<String, Object>> drawRoute(@RequestBody RequestData data) {
        try {
            routeService.setRequestData(data);

            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Rota hesaplama başarıyla alındı");

            Map<String, Object> routeDetails = new HashMap<>();
            routeDetails.put("start_location", data.getCurrentLocation());
            routeDetails.put("target_location", data.getTargetLocation());
            data.getPassenger().ifPresent(passenger -> routeDetails.put("passenger", passenger));
            data.getPaymentMethod().ifPresent(paymentMethod -> routeDetails.put("payment_method", paymentMethod));

            response.put("route_details", routeDetails);

            routeService.getRouteSummary();

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", "İstek işlenirken hata oluştu: " + e.getMessage());
            errorResponse.put("stackTrace", e.getStackTrace()[0].toString());
            e.printStackTrace();
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

}