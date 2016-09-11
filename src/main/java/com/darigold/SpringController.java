package com.darigold;

import com.darigold.model.CrimeModel;
import com.darigold.model.CsvDataEntity;
import com.darigold.model.GlobalMethods;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SpringController {

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @GetMapping("/search")
    public String searchPage() {
        return "search";
    }

    @PostMapping("/results")
    public String resultPage(ModelMap model, @RequestParam("address") String address, @RequestParam("distance") String distance, @RequestParam("dataYearsSearch") String years) throws Exception{

        List<CsvDataEntity> queryList = GlobalMethods.baseQuery();
        List<CrimeModel> crimeList = GlobalMethods.analyzeQuery(address,distance, years, queryList);

        model.put("crimeModel", crimeList);

        //if the list is empty, return a different view (no-results.html) that tells the user their search parameters were outside city etc.
        //TODO create no-results.html file
        if (crimeList.size() == 0) {
            return "results";
        } else {
            return "no-results";
        }

    }
}
