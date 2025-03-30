package org.transportationroutecalculation.prolab2_1_ver2.APİs;

import org.springframework.stereotype.Component;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.Controllers.PaymentController;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Passengers.Passengers;

@Component
public class PaymentControllerFactory {
    public PaymentController createController(Passengers passenger) {
        return new PaymentController(passenger);
    }
}