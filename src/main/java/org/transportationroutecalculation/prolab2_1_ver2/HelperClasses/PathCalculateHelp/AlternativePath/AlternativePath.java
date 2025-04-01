package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.AlternativePath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.transportationroutecalculation.prolab2_1_ver2.APİs.RequestData;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathClasses.Path2;
import org.transportationroutecalculation.prolab2_1_ver2.DataLoad.Data;
import org.transportationroutecalculation.prolab2_1_ver2.DataLoad.JsonLoadService;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.DistanceCalculate.DistanceCalculate;

@Service
public abstract class AlternativePath {

    private final DistanceCalculate distanceCalculate;
    private final Data data;

    @Autowired
    public AlternativePath(@Qualifier("haversine") DistanceCalculate distanceCalculate, JsonLoadService jsonLoadService) {
        this.distanceCalculate = distanceCalculate;
        this.data = jsonLoadService.getData();
    }

    public abstract Path2 calculatePath(RequestData frontend_data);

    public DistanceCalculate getDistanceCalculate() {
        return distanceCalculate;
    }

    public Data getData() {
        return data;
    }
}
