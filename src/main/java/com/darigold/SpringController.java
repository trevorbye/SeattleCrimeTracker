package com.darigold;

import com.darigold.model.CrimeModel;
import com.darigold.model.CrimeRank;
import com.darigold.model.GlobalMethods;
import com.darigold.model.SearchParamModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SpringController {

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @GetMapping("/search")
    public String searchPage(@ModelAttribute("searchFormBacking") SearchParamModel search, Model model) {

        if (model.asMap().containsKey("FormBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.searchFormBacking", model.asMap().get("FormBindingResult"));
        }

        if (!model.containsAttribute("searchFormBacking")) {
            model.addAttribute("searchFormBacking", new SearchParamModel());
        }

        return "search";
    }

    @GetMapping("/results")
    public String resultPageView(Model model, @ModelAttribute("searchFormBacking") SearchParamModel search, @ModelAttribute("crimeModel") List<CrimeModel> crimeList, @ModelAttribute("rankedModel") List<CrimeRank> rankedList) {
        model.addAttribute("crimeModel", crimeList);
        model.addAttribute("rankedModel", rankedList);
        model.addAttribute("searchTerms", search);

        if (crimeList.isEmpty()) {
            return "no-results";
        } else {
            return "results";
        }
    }

    @PostMapping("/results")
    @SuppressWarnings("unchecked")
    public String resultSubmit(@Valid @ModelAttribute("searchFormBacking") final SearchParamModel search, BindingResult result, final RedirectAttributes redirectAttributes) throws Exception{

        if (result.hasErrors()) {
            //flash errors bound to "searchFormBacking"
            redirectAttributes.addFlashAttribute("searchFormBacking",search);
            redirectAttributes.addFlashAttribute("FormBindingResult",result);
            return "redirect:/search";
        }

        List<Object[]> queryList = GlobalMethods.baseQuery();

        //input into model&view
        List<CrimeModel> crimeList = GlobalMethods.analyzeQuery(search.getSearchAddress(),search.getSearchDistance(),search.getSearchTime(), queryList);
        List<CrimeRank> rankedList = GlobalMethods.distinctAsList(GlobalMethods.rankedMap(GlobalMethods.distinctCountMap(crimeList)));

        redirectAttributes.addFlashAttribute("searchFormBacking",search);
        redirectAttributes.addFlashAttribute("crimeModel", crimeList);
        redirectAttributes.addFlashAttribute("rankedModel", rankedList);


        return "redirect:/results";

    }
}
