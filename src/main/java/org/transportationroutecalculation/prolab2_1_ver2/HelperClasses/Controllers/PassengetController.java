package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.Controllers;

import org.transportationroutecalculation.prolab2_1_ver2.APİs.RequestData;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Passengers.Passengers;

import java.util.HashMap;

public class PassengetController extends Controllers{

    public PassengetController(){
        super();
    }

    public RequestData passengerControl(HashMap<String ,RequestData> frontend_data, RequestData data){

        if (frontend_data.keySet().contains(data.getPassenger().map(Passengers::getNameSurname).orElse(" "))){
            return frontend_data.get(data.getPassenger().map(Passengers::getNameSurname).orElse(" "));
        }
        else{
            frontend_data.put(data.getPassenger().map(Passengers::getNameSurname).orElse(" "), data);
            return data;
        }

    }

}
