package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp;

public class TaxiTime {

    private final double speed = 60.0;

    public int calculateTime(double distance) {
        int totalMinutes = (int) ((distance / speed) * 60);
        return totalMinutes;
    }
}
