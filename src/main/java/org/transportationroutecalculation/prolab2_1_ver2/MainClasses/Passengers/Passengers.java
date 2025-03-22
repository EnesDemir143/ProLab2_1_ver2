package org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Passengers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.transportationroutecalculation.prolab2_1_ver2.Payment.PaymentMethods;

import java.util.Optional;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = General.class, name = "general"),
        @JsonSubTypes.Type(value = Students.class, name = "student"),
        @JsonSubTypes.Type(value = OldPeople.class, name = "old")
})
public abstract class Passengers {

    @JsonProperty("nameSurname")
    private String nameSurname;

    private Optional<PaymentMethods> paymentMethod;

    public Passengers() {
        this.paymentMethod = Optional.empty();
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

    public double getMoney() {
        return (double) paymentMethod
                .map(pm -> {
                    PassengersFunctions function = PassengersFunctions.valueOf(pm.getClass().getSimpleName().toUpperCase());
                    return function.getFunction().apply(pm);
                }).orElse(0.0);
    }
    public Optional<PaymentMethods> getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Optional<PaymentMethods> paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public abstract double getDiscountRate();

}