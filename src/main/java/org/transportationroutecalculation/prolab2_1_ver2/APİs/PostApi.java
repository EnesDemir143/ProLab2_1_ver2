package org.transportationroutecalculation.prolab2_1_ver2.APİs;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RestController;
    import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.Path_Calculate;
    import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.Route;
    import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.Controllers.PaymentController;


    import java.security.Principal;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Optional;


@RestController
    public class PostApi {

        private final Path_Calculate path_calculate;

        @Autowired
        public PostApi(Path_Calculate path_calculate) {
            this.path_calculate = path_calculate;
        }


        @PostMapping("/api/draw_route")
        public ResponseEntity<HashMap <String, List<Route>>> drawRoute(@RequestBody RequestData data, Principal principal) {
            try {
                HashMap <String, List<Route>> backEndReturn = path_calculate.path_calculate(data);
                
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