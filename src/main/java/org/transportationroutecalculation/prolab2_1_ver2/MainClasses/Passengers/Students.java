package org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Passengers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Students extends Passengers {
    @JsonProperty("student_id")
    private String studentId;

    public Students() {
        super();
    }

    @Override
    public double getDiscountRate() {
        return 0.5;
    }

    @JsonCreator
    public Students(@JsonProperty("nameSurname") String nameSurname, @JsonProperty("student_id") String studentId) {
        super(nameSurname);
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}