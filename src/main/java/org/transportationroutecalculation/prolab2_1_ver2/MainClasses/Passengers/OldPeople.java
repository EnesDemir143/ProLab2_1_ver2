package org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Passengers;

public class OldPeople extends Passengers {

    private String PassCard;

    public OldPeople(String nameSurname, String passCard) {
        super(nameSurname);
        PassCard = passCard;
    }

    public String getPassCard() {

        return PassCard;
    }

}