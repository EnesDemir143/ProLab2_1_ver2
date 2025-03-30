package org.transportationroutecalculation.prolab2_1_ver2.APİs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.Route2;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.Controllers.PaymentController;

import java.util.HashMap;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentControllerFactory paymentControllerFactory;

    @Autowired
    public PaymentService(PaymentControllerFactory paymentControllerFactory) {
        this.paymentControllerFactory = paymentControllerFactory;
    }

    public HashMap<String, List<Route2>> processPayment(@RequestBody RequestData data, HashMap<String, List<Route2>> backEndReturn) {
        PaymentController paymentController = paymentControllerFactory.createController(data.getPassenger().orElse(null));
        return paymentController.PayControl(backEndReturn);
    }
}