package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.DistanceCalculate;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.awt.geom.Point2D;


@Service
@Qualifier("euclidean")
public class EuclideanDistance implements DistanceCalculate {

    @Override
    public double calculateDistance(Point2D.Double source, Point2D.Double destination) {

        double x1 = source.getX();
        double y1 = source.getY();
        double x2 = destination.getX();
        double y2 = destination.getY();

        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}
