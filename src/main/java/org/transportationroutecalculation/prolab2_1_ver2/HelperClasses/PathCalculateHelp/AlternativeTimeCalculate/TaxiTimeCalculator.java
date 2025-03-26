package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.AlternativeTimeCalculate;

import org.springframework.stereotype.Service;

@Service
public class TaxiTimeCalculator implements TimeCalculator {
    private final double speed = 60.0;

    @Override
    public int calculateTime(double distance) {
        return (int) ((distance / speed) * 60);
    }
}
