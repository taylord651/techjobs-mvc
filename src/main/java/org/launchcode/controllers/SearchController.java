package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results

    @RequestMapping(value = "results")
    public String processSearchForm (Model model, @RequestParam String searchType, @RequestParam String searchTerm) {

        ArrayList<HashMap<String, String>> filteredJobs;

        if (searchType.equals("all")) {

            filteredJobs = JobData.findByValue(searchTerm);

            model.addAttribute("jobs", filteredJobs);

            int totalJobs = filteredJobs.size();

            model.addAttribute("total", totalJobs);

        } else {
            filteredJobs = JobData.findByColumnAndValue(searchType, searchTerm);

            int totalJobs = filteredJobs.size();

            model.addAttribute("columns", ListController.columnChoices);

            model.addAttribute("jobs", filteredJobs);

            model.addAttribute("total", totalJobs);
        }
        return "search";
    }
    }
