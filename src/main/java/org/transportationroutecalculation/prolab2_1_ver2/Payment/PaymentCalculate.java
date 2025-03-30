package org.transportationroutecalculation.prolab2_1_ver2.Payment;

import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.Route2;

import java.util.HashMap;
import java.util.List;

public interface PaymentCalculate {

    double calculateAmount(HashMap<String, List<Route2>> routes);
}
