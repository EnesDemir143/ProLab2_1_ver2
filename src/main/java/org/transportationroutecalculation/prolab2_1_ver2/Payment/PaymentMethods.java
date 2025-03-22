package org.transportationroutecalculation.prolab2_1_ver2.Payment;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.stereotype.Service;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.Route;

import java.util.HashMap;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CityCard.class, name = "cityCard"),
        @JsonSubTypes.Type(value = Cash.class, name = "cash"),
        @JsonSubTypes.Type(value = CreditCard.class, name = "creditCard")
})
@Service
public abstract class PaymentMethods implements PaymentCalculate{

    public PaymentMethods() {
    }

}