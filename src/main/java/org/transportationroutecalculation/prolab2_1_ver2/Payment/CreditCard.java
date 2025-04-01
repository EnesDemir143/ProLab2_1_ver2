package org.transportationroutecalculation.prolab2_1_ver2.Payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.Route2;

import java.util.HashMap;
import java.util.List;

public class CreditCard extends PaymentMethods{

    @JsonProperty("limit")
    private int limit;
    @JsonProperty("card_number")
    private String number;


    public CreditCard() {
        super();
    }

    @Override
    public double getMoney() {
        return getLimit();
    }

    @Override
    public double pay(double price) {
        if (price > getLimit()) {
            System.out.println("Not enough limit");
            return 0;
        } else {
            limit -= price;
            return limit;
        }
    }

    public CreditCard(int limit, String number) {
        this.limit = limit;
        this.number = number;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }



}
