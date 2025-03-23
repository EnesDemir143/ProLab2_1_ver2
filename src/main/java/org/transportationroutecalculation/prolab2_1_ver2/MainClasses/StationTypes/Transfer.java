package org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transfer {

    @JsonProperty("transferStopId")
    private String transferStopId;

    private Stations transferStation;

    @JsonProperty("transferUcret")
    private Double transfer_amount;

    private Double transfer_distance = 0.0;

    @JsonProperty("transferSure")
    private Integer transfer_time;

    public Transfer() {
    }

    public Transfer(String transferStopId, Double transfer_amount, Integer transfer_time) {
        this.transferStopId = transferStopId;
        this.transfer_amount = transfer_amount;
        this.transfer_time = transfer_time;
    }

    public String getTransferStopId() {
        return transferStopId;
    }

    public void setTransferStopId(String transferStopId) {
        this.transferStopId = transferStopId;
    }

    public Stations getTransferStation() {
        return transferStation;
    }

    public void setTransferStation(Stations transferStation) {
        this.transferStation = transferStation;
    }

    public Double getTransfer_amount() {
        return transfer_amount;
    }

    public void setTransfer_amount(Double transfer_amount) {
        this.transfer_amount = transfer_amount;
    }

    public Integer getTransfer_time() {
        return transfer_time;
    }

    public void setTransfer_time(Integer transfer_time) {
        this.transfer_time = transfer_time;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "transferStopId='" + transferStopId + '\'' +
                ", transferStation=" + transferStation +
                ", transfer_amount=" + transfer_amount +
                ", transfer_distance=" + transfer_distance +
                ", transfer_time=" + transfer_time +
                '}';
    }

    public Double getTransfer_distance() {
        return transfer_distance;
    }

    public void setTransfer_distance(Double transfer_distance) {
        this.transfer_distance = transfer_distance;
    }
}