package org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NextStation {

    @JsonProperty("stopId")
    private String stopId;

    private Stations Nextstation;

    @JsonProperty("mesafe")
    private Double distance;

    @JsonProperty("ucret")
    private Double amount;

    @JsonProperty("sure")
    private Integer time;

    public NextStation() {
    }

    public NextStation(String stopId, Double distance, Double amount, Integer time) {
        this.stopId = stopId;
        this.distance = distance;
        this.amount = amount;
        this.time = time;
    }

    public Stations getNextstation() {
        return Nextstation;
    }

    public void setNextstation(Stations nextstation) {
        Nextstation = nextstation;
    }

    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "NextStation{" +
                "stopId='" + stopId + '\'' +
                ", Nextstation=" + (Nextstation != null ? Nextstation.getStationID() : Nextstation) +
                ", distance=" + distance +
                ", amount=" + amount +
                ", time=" + time +
                '}';
    }
}