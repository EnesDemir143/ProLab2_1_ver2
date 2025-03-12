package org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transfer {

    @JsonProperty("transferStopId")
    private String transferStopId;

    @JsonProperty("transferUcret")
    private Double transfer_amount;

    @JsonProperty("transferSure")
    private Integer transfer_time;

    public Transfer() {
        // Default constructor for Jackson
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
                ", transfer_amount=" + transfer_amount +
                ", transfer_time=" + transfer_time +
                '}';
    }
}