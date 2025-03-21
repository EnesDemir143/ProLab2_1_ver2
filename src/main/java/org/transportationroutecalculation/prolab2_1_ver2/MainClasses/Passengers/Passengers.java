package org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Passengers;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = General.class, name = "general"),
        @JsonSubTypes.Type(value = Students.class, name = "student"),
        @JsonSubTypes.Type(value = OldPeople.class, name = "old")
})
public abstract class Passengers {
    private String nameSurname;

    public Passengers() {
    }

    public Passengers(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public abstract double getDiscountRate();

}