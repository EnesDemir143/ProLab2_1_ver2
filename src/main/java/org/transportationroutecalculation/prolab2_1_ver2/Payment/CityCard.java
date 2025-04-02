package org.transportationroutecalculation.prolab2_1_ver2.Payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Passengers.Passengers;

import java.util.HashMap;
import java.util.List;

public class CityCard extends PaymentMethods implements CalculateAmount {

    @JsonProperty("balance")
    private double balance;
    @JsonProperty("card_number")
    private double card_number;

    public CityCard() {
        super();
    }

    @Override
    public double getMoney() {
       return getBalance();
    }

    @Override
    public double pay(Passengers passenger, double price) {
        if (price > balance) {
            System.out.println("Not enough balance");
            return 0;
        } else {
            return balance - price;
        }
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
    public double calculateAmount(double price, double discountRate) {
        return price * discountRate;
    }
}
