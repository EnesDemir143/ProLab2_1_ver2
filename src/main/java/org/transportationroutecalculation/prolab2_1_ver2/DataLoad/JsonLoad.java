package org.transportationroutecalculation.prolab2_1_ver2.DataLoad;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Vehicles.Taxi;

import java.io.InputStream;
import java.util.logging.Logger;

@Service
public class JsonLoad implements JsonLoadService {

    private static final Logger LOGGER = Logger.getLogger(JsonLoad.class.getName());

    private String path;

    private Data data;

    private final ObjectMapper objectMapper;
    private final StationNextStationRelationships stationNextStationRelationships;
    private final StationTransferRelationships stationTransferRelationships;

    public JsonLoad(ObjectMapper objectMapper, StationNextStationRelationships stationNextStationRelationships, StationTransferRelationships stationTransferRelationships) {
        this.objectMapper = objectMapper;
        this.stationNextStationRelationships = stationNextStationRelationships;
        this.stationTransferRelationships = stationTransferRelationships;

    }

    @PostConstruct
    public void init() {
        load();
    }

    public void load() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("dataset/veriseti.json");
            if (inputStream == null) {
                throw new RuntimeException("JSON file not found in classpath: dataset/veriseti.json");
            }

            data = objectMapper.readValue(inputStream, Data.class);
            stationNextStationRelationships.map(data);
            stationTransferRelationships.map(data);

            Taxi taxi = data.getTaxi();
            LOGGER.info("City: " + data.getCity());
            LOGGER.info("Stations loaded: " + data.getStations().length);


        } catch (Exception e) {
            throw new RuntimeException("Error loading JSON. " + e.getMessage(), e);
        }
    }

    public Data getData() {
        return data;
    }
}