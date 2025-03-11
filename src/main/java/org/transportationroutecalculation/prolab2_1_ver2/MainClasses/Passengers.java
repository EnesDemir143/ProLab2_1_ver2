package org.transportationroutecalculation.prolab2_1_ver2.MainClasses;

public abstract class Passengers {

    private String nameSurname;

    public Passengers(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getNameSurname() {
        return nameSurname;
    }
}

class General extends Passengers {

    public General(String nameSurname) {
        super(nameSurname);
    }
}



class Students extends Passengers {

    private String studentId;

    public Students(String nameSurname, String studentId) {
        super(nameSurname);
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

}


class OldPeople extends Passengers {

    private String PassCard;

    public OldPeople(String nameSurname, String passCard) {
        super(nameSurname);
        PassCard = passCard;
    }

    public String getPassCard() {

        return PassCard;
    }

}