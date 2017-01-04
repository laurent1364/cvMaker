package mirage.springframework.controllers;

import mirage.springframework.domain.Referee;
import mirage.springframework.domain.UserDetails;
import mirage.springframework.services.data.RefereeService;
import mirage.springframework.services.data.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Mirage on 17/12/2016.
 */
@Controller
public class RefereeController {

    private RefereeService refereeService;
    private UserDetailsService userDetailsService;

    @Autowired
    public void setRefereeService(RefereeService refereeService) {
        this.refereeService = refereeService;
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping("/user/{id}/referees")
    public String listReferees(@PathVariable Integer id, Model model){
        UserDetails userDetails = userDetailsService.getById(id);
        model.addAttribute("referees", userDetails.getReferees());
        model.addAttribute("user", userDetails);
        return "admin/referees/refereedetails";
    }

    @RequestMapping("/user/{id}/referee/new")
    public String newReferee(@PathVariable Integer id, Model model){
        Referee referee = new Referee();
        referee.setUserDetails(userDetailsService.getById(id));
        model.addAttribute("referee", referee);
        return "admin/referees/refereeform";
    }

    @RequestMapping("/referee/edit/{id}")
    public String editReferee(@PathVariable Integer id, Model model){
        model.addAttribute("referee", refereeService.getById(id));
        return "admin/referees/refereeform";
    }

    @RequestMapping(value = "/referees", method = RequestMethod.POST)
    public String saveReferee(@Valid Referee referee, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            for(ObjectError objectError : bindingResult.getAllErrors()){
                System.out.println(objectError.toString());
            }
            return "admin/referees/refereeform";
        }
        refereeService.save(referee);

        return "redirect:/user/" + referee.getUserDetails().getId() + "/referees";
    }

    @RequestMapping("/referee/delete/{id}")
    public String deleteReferee(@PathVariable Integer id){
        UserDetails userDetails = refereeService.getById(id).getUserDetails();
        refereeService.delete(id);
        return "redirect:/user/" + userDetails.getId() + "/referees";
    }
}
