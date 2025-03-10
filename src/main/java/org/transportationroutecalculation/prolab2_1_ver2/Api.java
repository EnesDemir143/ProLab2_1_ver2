package org.transportationroutecalculation.prolab2_1_ver2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Api {

    @Value("${API_KEY}")
    private String apiKey;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("api_key", apiKey);
        // Assuming 'stops' data is static or comes from somewhere else
        model.addAttribute("stops", getStopsData()); // Replace with your actual stops data source
        return "index"; // Refers to src/main/resources/templates/index.html (without .html extension)
    }

    // Dummy method for stops data (replace with your actual logic)
    private String getStopsData() {
        // Example JSON data for stops (replace with real data source)
        return "[{\"ids\":\"Stop1\",\"lat\":40.7669,\"lon\":29.9408,\"types\":\"bus\"}]";
    }
}