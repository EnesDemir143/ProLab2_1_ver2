package org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star;

public enum PathType {
    BUS("bus"),
    TRAM("tram"),
    noType("noType");
    private final String type;

    PathType(String type) {
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