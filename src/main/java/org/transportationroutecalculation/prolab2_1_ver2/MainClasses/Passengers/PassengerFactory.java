package org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Passengers;

public class PassengerFactory {
    public static Passengers createPassenger(String type) {
        switch (type.toLowerCase()) {
            case "general": return new General();
            case "old": return new OldPeople();
            case "student": return new Students();
            default: throw new IllegalArgumentException("Geçersiz yolcu tipi: " + type);
        }
    }
}
