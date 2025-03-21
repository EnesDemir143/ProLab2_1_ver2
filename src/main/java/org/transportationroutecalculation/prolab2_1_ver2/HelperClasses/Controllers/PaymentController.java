package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Passengers.Passengers;


public class PaymentController extends Controllers{

    private Passengers passenger;

    public PaymentController(Passengers passenger) {
        this.passenger = passenger;
    }

    public Boolean PayControl(){

        int money = passenger.getMoney();
        double amount = passenger.getPaymentMethod().calculateAmount();

        if(amount < 0){
            return false;
        }

        else if (amount )


        return false;
    }
}
