package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;
import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.props;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model, @RequestParam(value = "searchType", required = false) String searchType) {
        model.addAttribute("columns", columnChoices);
        model.addAttribute("props", props);
        if (StringUtils.isEmpty(searchType)) {
            searchType = "all";
        }
        model.addAttribute("searchType", searchType);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    @RequestMapping(value = "results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        ArrayList<Job> jobs;
        if (searchTerm.equalsIgnoreCase("all") || StringUtils.isEmpty(searchTerm)) {
            jobs = JobData.findAll();
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        model.addAttribute("columns", columnChoices);
        model.addAttribute("jobs", jobs);
        model.addAttribute("searchType", searchType);
        model.addAttribute("props", props);


        return "search";
    }

//    @GetMapping(value = "redirect")
//    public String getNewSearchResults(RedirectAttributes model, @RequestParam String searchType, @RequestParam String searchTerm) {
//        model.addFlashAttribute("searchType", searchType);
//        model.addFlashAttribute("searchTerm", searchTerm);
//        return "redirect:results";
//    }

}
