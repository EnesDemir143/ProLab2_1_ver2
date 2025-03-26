package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.StationStatusHandler;

import org.transportationroutecalculation.prolab2_1_ver2.APİs.RequestData;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathRecords.Path;

public interface StationHandler {
    void processStation(RequestData requestData, Path path);
}