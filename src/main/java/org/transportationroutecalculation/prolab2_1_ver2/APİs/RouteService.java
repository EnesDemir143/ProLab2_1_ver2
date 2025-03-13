package org.transportationroutecalculation.prolab2_1_ver2.APİs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RouteService {
    private RequestData currentRequestData;
    private static final Logger logger = LoggerFactory.getLogger(RouteService.class);

    public void setRequestData(RequestData requestData) {
        this.currentRequestData = requestData;
    }

    public RequestData getRequestData() {
        return currentRequestData;
    }

    public void getRouteSummary() {
        logger.info("Rota özeti: {} -> {}", currentRequestData.getCurrentLocation().getLocation().getX(), currentRequestData.getTargetLocation().getLocation().getX());
        System.out.println("Rota özeti: " + currentRequestData.getCurrentLocation().getLocation().getY() + " -> " + currentRequestData.getTargetLocation().getLocation().getY());
    }
}