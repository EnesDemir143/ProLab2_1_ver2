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
            RequestData oldData  = frontend_data.get(data.getPassenger().map(Passengers::getNameSurname).orElse(" "));

            oldData.setCurrentLocation(data.getCurrentLocation());
            oldData.setTargetLocation(data.getTargetLocation());
            oldData.setPaymentMethod(data.getPaymentMethod());

            frontend_data.put(data.getPassenger().map(Passengers::getNameSurname).orElse(" "), oldData);

            System.out.println("Passenger already exists, updating data.");
            return oldData;
        }
        else{
            frontend_data.put(data.getPassenger().map(Passengers::getNameSurname).orElse(" "), data);
            return data;
        }

    }

}
