package org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Passengers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OldPeople extends Passengers {
    @JsonProperty("pass_card")
    private String passCard;

    public OldPeople() {
        super();
    }

    @Override
    public double getDiscountRate() {
        return 0.6;
    }

    @JsonCreator
    public OldPeople(@JsonProperty("nameSurname") String nameSurname, @JsonProperty("pass_card") String passCard) {
        super(nameSurname);
        this.passCard = passCard;
    }

    public String getPassCard() {
        return passCard;
    }

    public void setPassCard(String passCard) {
        this.passCard = passCard;
    }
}