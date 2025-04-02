package org.transportationroutecalculation.prolab2_1_ver2.Payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Passengers.Passengers;

import java.util.HashMap;
import java.util.List;

public class Cash extends PaymentMethods implements CalculateAmount{

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
    public double pay(Passengers passenger, double price) {
        if (price > cash) {
            System.out.println("Not enough cash");
            return 0;
        }
        else {
            return cash - price;
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

    @Override
    public double calculateAmount(double price, double discountRate) {
        return price * discountRate;
    }
}
