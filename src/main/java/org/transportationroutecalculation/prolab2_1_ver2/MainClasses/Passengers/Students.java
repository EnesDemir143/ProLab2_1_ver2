package org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Passengers;

public class Students extends Passengers {

    private String studentId;

    public Students(String nameSurname, String studentId) {
        super(nameSurname);
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

}
