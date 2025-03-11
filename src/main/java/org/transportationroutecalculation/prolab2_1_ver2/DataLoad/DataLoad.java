package org.transportationroutecalculation.prolab2_1_ver2.DataLoad;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

class Data{



}




public class JsonLoad {

    private String path;

    public double load(){

        ObjectMapper objectMapper = new ObjectMapper();

        try{

            data = objectMapper.readValue(new File(path), data.classes)
        }

    }

}
