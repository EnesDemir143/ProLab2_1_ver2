package org.transportationroutecalculation.prolab2_1_ver2.DataLoad;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Vehicles.Taxi;

import java.io.File;
import java.util.logging.Logger;

@Service
public class JsonLoad implements JsonLoadService {

    private static final Logger LOGGER = Logger.getLogger(JsonLoad.class.getName());

    @Value("${json.file.path}")
    private String path;
    private Data data;

    private final ObjectMapper objectMapper;
    private final StationRelationshipMapper relationshipMapper;

    public JsonLoad(ObjectMapper objectMapper, StationRelationshipMapper relationshipMapper) {
        this.objectMapper = objectMapper;
        this.relationshipMapper = relationshipMapper;
    }

    @PostConstruct
    public void init() {
        load();
    }

    public double load() {
        try {
            data = objectMapper.readValue(new File(path), Data.class);
            relationshipMapper.mapRelationships(data);

            Taxi taxi = data.getTaxi();
            double distance = 10.0;
            double totalCost = taxi.calculate_price(distance);

            LOGGER.info("City: " + data.getCity());
            LOGGER.info("Stations loaded: " + data.getStations().length);

            return totalCost;

        } catch (Exception e) {
            throw new RuntimeException("Error loading JSON from: " + path + ". " + e.getMessage(), e);
        }
    }

    public Data getData() {
        return data;
    }
}