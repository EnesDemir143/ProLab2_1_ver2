package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.Controllers;

import org.transportationroutecalculation.prolab2_1_ver2.APİs.RequestData;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Passengers.Passengers;

import java.util.HashMap;

public class PassengerController extends Controllers{

    public PassengerController(){
        super();
    }

    public RequestData passengerControl(HashMap<String ,RequestData> frontend_data, RequestData data){

        if (frontend_data.containsKey(data.getPassenger().map(Passengers::getNameSurname).orElse(" "))){
            return frontend_data.get(data.getPassenger().map(Passengers::getNameSurname).orElse(" "));
        }
        else{
            frontend_data.put(data.getPassenger().map(Passengers::getNameSurname).orElse(" "), data);
            return data;
        }

    }

}
