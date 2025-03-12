package org.transportationroutecalculation.prolab2_1_ver2.Payment;

public class CityCard extends PaymentMethods{

    private double balance;
    private double card_number;

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
    double pay() {
        return 0;
    }
}
