package org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Passengers;

public abstract class Passengers {

    private String nameSurname;

    public Passengers(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getNameSurname() {
        return nameSurname;
    }
}