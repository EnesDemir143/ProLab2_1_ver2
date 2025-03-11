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
        model.addAttribute("stops", getStopsData());
        return "index";
    }

    private String getStopsData() {
        return "[{\"ids\":\"Stop1\",\"lat\":40.7669,\"lon\":29.9408,\"types\":\"bus\"}]";
    }
}