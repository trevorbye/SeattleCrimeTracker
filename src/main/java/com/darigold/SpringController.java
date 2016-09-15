package com.darigold;

import com.darigold.model.CrimeModel;
import com.darigold.model.CrimeRank;
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
    @SuppressWarnings("unchecked")
    public String resultPage(ModelMap model, @RequestParam("address") String address, @RequestParam("distance") String distance, @RequestParam("dataYearsSearch") String years) throws Exception{

        //TODO clear queryList after query to avoid leaving in memory
        List<Object[]> queryList = GlobalMethods.baseQuery();

        //input into model&view
        List<CrimeModel> crimeList = GlobalMethods.analyzeQuery(address,distance, years, queryList);
        List<CrimeRank> rankedList = GlobalMethods.distinctAsList(GlobalMethods.rankedMap(GlobalMethods.distinctCountMap(crimeList)));


        model.put("crimeModel", crimeList);
        model.put("rankedModel", rankedList);

        //if the list is empty, return a different view (no-results.html) that tells the user their search parameters were outside city etc.
        if (crimeList.isEmpty()) {
            return "no-results";
        } else {
            return "results";
        }

    }
}
