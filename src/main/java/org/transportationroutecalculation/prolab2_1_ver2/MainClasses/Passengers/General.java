package org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Passengers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class General extends Passengers {

    public General() {
        super();
    }

    @JsonCreator
    public General(@JsonProperty("nameSurname") String nameSurname) {
        super(nameSurname);
    }

    @Override
    public double getDiscountRate() {
        return 1;
    }

}
