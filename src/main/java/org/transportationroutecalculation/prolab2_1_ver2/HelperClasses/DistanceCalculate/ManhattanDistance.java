package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.DistanceCalculate;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.awt.geom.Point2D;

@Service
@Qualifier("manhattan")
public class ManhattanDistance implements DistanceCalculate {

    @Override
    public double calculateDistance(Point2D.Double source, Point2D.Double destination) {

        double x1 = source.getX();
        double y1 = source.getY();
        double x2 = destination.getX();
        double y2 = destination.getY();

        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
