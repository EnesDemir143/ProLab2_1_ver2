package org.transportationroutecalculation.prolab2_1_ver2.Payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.Route;

import java.util.HashMap;
import java.util.List;

public class CityCard extends PaymentMethods{

    @JsonProperty("balance")
    private double balance;
    @JsonProperty("card_number")
    private double card_number;

    public CityCard() {
        super();
    }

    public CityCard(double balance, double card_number) {
        this.balance = balance;
        this.card_number = card_number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getCard_number() {
        return card_number;
    }

    public void setCard_number(double card_number) {
        this.card_number = card_number;
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
