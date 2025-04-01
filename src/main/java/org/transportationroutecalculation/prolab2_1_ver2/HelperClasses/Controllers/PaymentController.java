package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.Controllers;

import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.Route2;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Passengers.Passengers;
import org.transportationroutecalculation.prolab2_1_ver2.Payment.PaymentMethods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class PaymentController extends Controllers{

    private Passengers passenger;

    public PaymentController(Passengers passenger) {
        this.passenger = passenger;
    }

    public HashMap<String, List<Route2>> PayControl(HashMap<String, List<Route2>> routes) {
        List<Route2> routeList = routes.get("routes");
        if (routeList == null) {
            return routes;
        }


        return routes;
    }
}
