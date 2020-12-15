package com.rahul.Coronavirustracker.Controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rahul.Coronavirustracker.Services.CoronaVirusDataService;
import com.rahul.Coronavirustracker.models.LocationsStats;

@Controller
public class HomeController {

    @Autowired
    private CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model){
    	List<LocationsStats> allStats=coronaVirusDataService.getAllStats();
    	int totalCases=allStats.stream().mapToInt(stat->stat.getLatestTotalCases()).sum();
    	int newCases=allStats.stream().mapToInt(stat->stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats",allStats);
        model.addAttribute("totalReportedCases", totalCases);
        model.addAttribute("newCasesFromPrevDay",newCases);
        return "index";
    }
}
