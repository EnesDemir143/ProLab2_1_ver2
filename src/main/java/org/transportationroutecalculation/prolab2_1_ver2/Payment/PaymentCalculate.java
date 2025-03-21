package org.transportationroutecalculation.prolab2_1_ver2.Payment;

import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.Route;

import java.util.HashMap;
import java.util.List;

public interface PaymentCalculate {

    double calculateAmount(HashMap<String, List<Route>> routes, int index);
}
