package org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Passengers;

import org.transportationroutecalculation.prolab2_1_ver2.Payment.Cash;
import org.transportationroutecalculation.prolab2_1_ver2.Payment.CityCard;
import org.transportationroutecalculation.prolab2_1_ver2.Payment.CreditCard;

import java.util.function.Function;

public enum PassengersFunctions {

    CREDİTCARD(creditCard -> ((CreditCard)creditCard).getLimit()),
    CİTYCARD(cityCard -> ((CityCard)cityCard).getBalance()),
    CASH(cash -> ((Cash)cash).getCash());

    private final Function<Object, Object> function;

    PassengersFunctions(Function<Object, Object> function) {
        this.function = function;
    }
    public Function<Object, Object> getFunction() {
        return function;
    }

    public Object getPaymentMethod() {
        return switch (this) {
            case CREDİTCARD -> "Credit Card";
            case CİTYCARD -> "City Card";
            case CASH -> "Cash";
        };
    }
}