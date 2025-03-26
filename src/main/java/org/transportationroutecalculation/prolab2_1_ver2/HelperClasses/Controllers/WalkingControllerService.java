package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.Controllers;

import java.awt.geom.Point2D;
import java.util.AbstractMap;

public interface WalkingControllerService {
    AbstractMap.SimpleEntry<Boolean, Double> can_proceed(Point2D.Double start, Point2D.Double end);
}