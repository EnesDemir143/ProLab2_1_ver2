package org.transportationroutecalculation.prolab2_1_ver2.Payment;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.stereotype.Service;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Passengers.Passengers;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CityCard.class, name = "cityCard"),
        @JsonSubTypes.Type(value = Cash.class, name = "cash"),
        @JsonSubTypes.Type(value = CreditCard.class, name = "creditCard")
})
@Service
public abstract class PaymentMethods implements CalculateAmount{

    public PaymentMethods() {
    }

    public abstract double getMoney();
    public abstract double pay(Passengers passenger, double price);

}