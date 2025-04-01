package org.transportationroutecalculation.prolab2_1_ver2.Payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.Route2;

import java.util.HashMap;
import java.util.List;

public class Cash extends PaymentMethods{

    @JsonProperty("cash")
    private double cash;

    public Cash() {
        super();
    }

    @Override
    public double getMoney() {
        return getCash();
    }

    @Override
    public double pay(double price) {
        if (price > cash) {
            System.out.println("Not enough cash");
            return 0;
        }
        else {
            cash -= price;
            return cash;
        }
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


}
