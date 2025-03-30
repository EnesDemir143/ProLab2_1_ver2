package org.transportationroutecalculation.prolab2_1_ver2.DataLoad;

import org.springframework.stereotype.Component;

@Component
public class GraphDataLoader {

    private final JsonLoadService jsonLoadService;

    public GraphDataLoader(JsonLoadService jsonLoadService) {
        this.jsonLoadService = jsonLoadService;
    }

    public Data loadGraphData() {
        return jsonLoadService.getData();
    }
}