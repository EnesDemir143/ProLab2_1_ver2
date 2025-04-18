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

    private int enterCount = 0;

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

    public Optional<PaymentMethods> getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Optional<PaymentMethods> paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getEnterCount() {
        return enterCount;
    }

    public void setEnterCount() {
        this.enterCount += 1;
    }

    public abstract double getDiscountRate();

}