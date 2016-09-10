package com.darigold;

import com.darigold.model.SearchModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        
        
        //do DB stuff to find crime and occurance count

        
        
        
        SearchModel searchModel = new SearchModel(address,1,5, "Murder", "150" );
        model.put("searchModel", searchModel);
        return "results";
    }
}
