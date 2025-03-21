package org.transportationroutecalculation.prolab2_1_ver2.Payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.Route;

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

    @Override
    public double pay() {

        return 0;
    }

    @Override
    public double calculateAmount(HashMap<String, List<Route>> routes, int index) {

        double amount = routes.get("route").get(index).amount().get();

        return amount;
    }
}
