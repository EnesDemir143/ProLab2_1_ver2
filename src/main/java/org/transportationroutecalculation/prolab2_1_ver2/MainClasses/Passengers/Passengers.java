package org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Passengers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.stereotype.Service;
import org.transportationroutecalculation.prolab2_1_ver2.Payment.PaymentMethods;

import java.util.Optional;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = General.class, name = "general"),
        @JsonSubTypes.Type(value = Students.class, name = "student"),
        @JsonSubTypes.Type(value = OldPeople.class, name = "old")
})
@Service
public abstract class Passengers {
    private String nameSurname;

    private Optional<PaymentMethods> paymentMethod;

    public Passengers() {
    }

    public Passengers(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public int getMoney() {
        return paymentMethod
                .map(pm -> PassengersFunctions.valueOf(pm.getClass().getSimpleName().toUpperCase()).getFunction().apply(pm))
                .map(value -> value instanceof Number ? ((Number) value).intValue() : 0)
                .orElse(0);
    }

    public Optional<PaymentMethods> getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Optional<PaymentMethods> paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public abstract double getDiscountRate();

}