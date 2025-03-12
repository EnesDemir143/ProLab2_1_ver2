package org.transportationroutecalculation.prolab2_1_ver2.APİs;

import org.springframework.stereotype.Service;

@Service
public class RouteService {
    private RequestData currentRequestData;

    public void setRequestData(RequestData requestData) {
        this.currentRequestData = requestData;
    }

    public RequestData getRequestData() {
        return currentRequestData;
    }

    public String getRouteSummary() {
        if (currentRequestData == null) {
            return "Henüz veri yok.";
        }
        StringBuilder summary = new StringBuilder();
        summary.append("Start: ").append(currentRequestData.getCurrentLocation()).append("\n");
        summary.append("Target: ").append(currentRequestData.getTargetLocation());
        return summary.toString();
    }
}