package mirage.springframework.controllers;

import mirage.springframework.domain.UserDetails;
import mirage.springframework.services.data.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Mirage on 17/12/2016.
 */
@Controller
public class UserDetailsController {

    private UserDetailsService userDetailsService;

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping("/user/{id}")
    public String getUserDetails(@PathVariable Integer id, Model model){
        UserDetails userDetails = userDetailsService.getById(id);
        model.addAttribute("user", userDetails);
        model.addAttribute("experiencesCount", userDetails.getExperiences().size());
        model.addAttribute("educationsCount", userDetails.getEducations().size());
        model.addAttribute("domainsCount", userDetails.getDomains().size());
        model.addAttribute("refereesCount", userDetails.getReferees().size());
        return "admin/userdetails/userdetails";
    }

}
