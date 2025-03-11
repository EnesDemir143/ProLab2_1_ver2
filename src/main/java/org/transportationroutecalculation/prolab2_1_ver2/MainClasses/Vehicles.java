package org.transportationroutecalculation.prolab2_1_ver2.MainClasses;

public abstract class Vehicles {

    private String city;

    public abstract String getCity();

}
class Taxi extends Vehicles {

    private Double opening_fee;
    private double cost_per_km;


    @Override
    public String getCity() {
        return "Taxi";
    }

    public Double calculate_price(Double distance) {
        return  opening_fee +  (distance * cost_per_km);
    }

}


class Bus extends Vehicles {

    private String name;


    @Override
    public String getCity() {
        return "Bus";
    }
}


class  Tram extends Vehicles {

    private String name;


    @Override
    public String getCity() {
        return "Tram";
    }
}