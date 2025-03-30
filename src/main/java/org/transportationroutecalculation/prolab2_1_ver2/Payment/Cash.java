package org.transportationroutecalculation.prolab2_1_ver2.Payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.Route2;

import java.util.HashMap;
import java.util.List;

public class Cash extends PaymentMethods implements PaymentCalculate{

    @JsonProperty("cash")
    private double cash;

    public Cash() {
        super();
    }

    @Override
    public double getMoney() {
        return getCash();
    }

    public Cash(double cash) {
        this.cash = cash;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }


    @Override
    public double calculateAmount(HashMap<String, List<Route2>> routes) {

        return 0;
    }
}
