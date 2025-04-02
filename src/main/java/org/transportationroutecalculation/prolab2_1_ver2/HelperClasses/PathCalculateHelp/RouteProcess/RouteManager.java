package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.RouteProcess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.transportationroutecalculation.prolab2_1_ver2.APİs.RequestData;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.Metric;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathClasses.Path2;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.AlternativePath.AlternativePath;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.StationStatusHandler.AfterStation;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.StationStatusHandler.BeforeStation;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Passengers.Passengers;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class RouteManager {

    private final PathFactory pathFactory;
    private final BeforeStation beforeStation;
    private final AfterStation afterStation;
    private final AlternativePath alternativePath;

    @Autowired
    public RouteManager(PathFactory pathFactory, BeforeStation beforeStation, AfterStation afterStation, AlternativePath alternativePath) {
        this.pathFactory = pathFactory;
        this.beforeStation = beforeStation;
        this.afterStation = afterStation;
        this.alternativePath = alternativePath;
    }

    public HashMap<String, List<Path2>> createRoutes(RequestData frontend_data, Stations startStation, Stations endStation, String type) {
        HashMap<String, List<Path2>> backEndReturn = new HashMap<>();
        List<Path2> paths = new ArrayList<>();

        for (Metric metric : Metric.values()) {
            System.out.println("RouteManager: Metric: " + metric);
            Path2 path = pathFactory.createPath(startStation, endStation, metric, type);
            if (path == null) {
                System.err.println("RouteManager: Path not found for metric: " + metric);
                continue;
            }

            beforeStation.processStation(frontend_data, path);
            afterStation.processStation(frontend_data, path);

            double originalAmount = path.getAmount();
            double discountRate = frontend_data.getPassenger().map(Passengers::getDiscountRate).orElse(1.0);
            double finalAmount = frontend_data.getPaymentMethod()
                    .map(paymentMethod -> paymentMethod.calculateAmount(originalAmount, discountRate))
                    .orElse(originalAmount);
            path.setAmount(finalAmount);

            paths.add(path);
        }

        if (!paths.isEmpty()) {
            backEndReturn.put("routes", paths);
        }

        return backEndReturn;
    }
}