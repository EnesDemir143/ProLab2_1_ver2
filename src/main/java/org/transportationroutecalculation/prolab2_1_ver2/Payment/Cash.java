package org.transportationroutecalculation.prolab2_1_ver2.Payment;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cash extends PaymentMethods{

    @JsonProperty("cash")
    private double cash;

    public Cash() {
        super();
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
    double pay() {
        return 0;
    }
}
