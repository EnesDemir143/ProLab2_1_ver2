package org.transportationroutecalculation.prolab2_1_ver2.APİs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.transportationroutecalculation.prolab2_1_ver2.DataLoad.Data;
import org.transportationroutecalculation.prolab2_1_ver2.DataLoad.JsonLoadService;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GetApi {

    @Value("${API_KEY}")
    private String apiKey;

    private final Data data;
    private final JsonLoadService jsonLoadService;
    private List<Map<String, Object>> stationsList = new ArrayList<>();

    @Autowired
    public GetApi(JsonLoadService jsonLoadService ) {
        this.jsonLoadService = jsonLoadService;
        this.data = jsonLoadService.getData();
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("api_key", apiKey);
        model.addAttribute("stops", getStopsData());
        return "index";
    }

    private List<Map<String, Object>> getStopsData() {
        List<Map<String, Object>> stationsList = new ArrayList<>();
        for (Stations station : data.getStations()) {
            Map<String, Object> stationMap = new HashMap<>();
            stationMap.put("ids", station.getStationID());
            stationMap.put("types", station.getStationType());
            stationMap.put("lat", station.getLocation().getX());
            stationMap.put("lon", station.getLocation().getY());
            stationsList.add(stationMap);
        }
        return stationsList;
    }
}