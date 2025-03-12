package org.transportationroutecalculation.prolab2_1_ver2.Payment;

public class CreditCard extends PaymentMethods{

    private int limit;
    private String number;

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
    double pay() {
        return 0;
    }
}
