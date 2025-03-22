package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.Route;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Passengers.Passengers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class PaymentController extends Controllers{

    private Passengers passenger;

    public PaymentController(Passengers passenger) {
        this.passenger = passenger;
    }

    public HashMap<String, List<Route>> PayControl(HashMap<String, List<Route>> routes) {
        List<Route> routeList = routes.get("routes");
        if (routeList == null) {
            return routes;
        }

        List<Route> mutableRouteList = new ArrayList<>(routeList);
        List<Route> routesToRemove = new ArrayList<>();

        double money = passenger.getMoney();
        System.out.println("asdasdadd"+money);

        for (Route route : mutableRouteList) {
            if (money < 0) {
                return routes;
            } else if (money < passenger.getDiscountRate()*(route.amount().get())) {
                routesToRemove.add(route);
            } else {
                route.amount().set(passenger.getDiscountRate()*(route.amount().get()));
                System.out.println("Ödeme başarılı");
            }
        }

        mutableRouteList.removeAll(routesToRemove);
        routes.put("routes", mutableRouteList);
        return routes;
    }
}
