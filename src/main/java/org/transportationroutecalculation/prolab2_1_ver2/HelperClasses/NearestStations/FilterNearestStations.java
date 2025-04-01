package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.NearestStations;

import org.springframework.stereotype.Service;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FilterNearestStations {

    public Stations filterStation(List<Distances<Stations, Double>> neareststations, String type) {
        return neareststations.stream()
                .filter(x -> "notype".equals(type) || x.stations().getStationType().equals(type))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No suitable station found"))
                .stations();
    }
}
