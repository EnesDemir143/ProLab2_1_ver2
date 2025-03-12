package org.transportationroutecalculation.prolab2_1_ver2.Payment;

public class Cash extends PaymentMethods{

    private double cash;

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
