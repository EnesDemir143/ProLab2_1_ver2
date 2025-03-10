package org.transportationroutecalculation.prolab2_1_ver2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Api {

    private String API_KEY;


    public  String index(){
        return "index.html";
    }

}
