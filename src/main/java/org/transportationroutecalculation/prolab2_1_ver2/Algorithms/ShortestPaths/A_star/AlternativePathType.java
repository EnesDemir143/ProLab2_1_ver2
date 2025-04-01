package org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star;

import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.AlternativePath.AlternativePath;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.AlternativePath.TaxiPath;

public enum AlternativePathType {

    TAXI("taxi");
    private final String type;

    AlternativePathType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static String[] getTypes() {
        String[] types = new String[values().length];
        for (int i = 0; i < values().length; i++) {
            types[i] = values()[i].getType();
        }
        return types;
    }

}
