package org.transportationroutecalculation.prolab2_1_ver2.APİs;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Locations.CurrentLocation;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Locations.TargetLocation;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Passengers.Passengers;
import org.transportationroutecalculation.prolab2_1_ver2.Payment.PaymentMethods;

import java.util.Optional;

public class RequestData {
    @JsonProperty("start_location")
    private CurrentLocation currentLocation;

    @JsonProperty("start_location")
    private TargetLocation targetLocation;

    @JsonProperty("start_location")
    private Optional<Passengers> passenger;

    @JsonProperty("start_location")
    private Optional<PaymentMethods> paymentMethod;

    public RequestData() {
        this.passenger = Optional.empty();
        this.paymentMethod = Optional.empty();
    }

    public RequestData(CurrentLocation currentLocation, TargetLocation targetLocation, Optional<Passengers> passenger, Optional<PaymentMethods> paymentMethod) {
        this.currentLocation = currentLocation;
        this.targetLocation = targetLocation;
        this.passenger = passenger;
        this.paymentMethod = paymentMethod;
    }

    public CurrentLocation getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(CurrentLocation currentLocation) {
        this.currentLocation = currentLocation;
    }

    public TargetLocation getTargetLocation() {
        return targetLocation;
    }

    public void setTargetLocation(TargetLocation targetLocation) {
        this.targetLocation = targetLocation;
    }

    public Optional<Passengers> getPassenger() {
        return passenger;
    }

    public void setPassenger(Optional<Passengers> passenger) {
        this.passenger = passenger;
    }

    public Optional<PaymentMethods> getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Optional<PaymentMethods> paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "RequestData{" +
                "currentLocation=" + currentLocation +
                ", targetLocation=" + targetLocation +
                ", passenger=" + passenger +
                ", paymentMethod=" + paymentMethod +
                '}';
    }
}
