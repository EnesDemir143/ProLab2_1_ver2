package org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Vehicles;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Taxi extends Vehicles {

    @JsonProperty("openingFee")
    private Double opening_fee;

    @JsonProperty("costPerKm")
    private Double cost_per_km;

    @Override
    public String getType() {
        return "Taxi";
    }

    public Double getOpening_fee() {
        return opening_fee;
    }

    public void setOpening_fee(Double opening_fee) {
        this.opening_fee = opening_fee;
    }

    public Double getCost_per_km() {
        return cost_per_km;
    }

    public void setCost_per_km(Double cost_per_km) {
        this.cost_per_km = cost_per_km;
    }

    public Double calculate_price(Double distance) {
        return opening_fee + (distance * cost_per_km);
    }
}