package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.DistanceCalculate;

import java.awt.geom.Point2D;

public class HaversineDistance implements DistanceCalculate {

    private static final Integer R = 6371;

    @Override
    public double calculateDistance(Point2D.Double source, Point2D.Double destination) {

        double lat1 = Math.toRadians(source.getX());
        double lon1 = Math.toRadians(source.getY());
        double lat2 = Math.toRadians(destination.getX());
        double lon2 = Math.toRadians(destination.getY());

        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;

        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }
}
