package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.DistanceCalculate.DistanceCalculate;

@Service
public class TaxiControl extends Controllers{

    private DistanceCalculate distanceCalculate;

    @Autowired
    public TaxiControl(@Qualifier("haversine") DistanceCalculate distanceCalculate) {
        this.distanceCalculate = distanceCalculate;
    }


}
