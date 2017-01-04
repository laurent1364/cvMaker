package mirage.springframework.controllers;

import mirage.springframework.domain.Experience;
import mirage.springframework.domain.UserDetails;
import mirage.springframework.services.data.ExperienceService;
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
public class ExperienceController{

    private ExperienceService experienceService;
    private UserDetailsService userDetailsService;

    @Autowired
    public void setExperienceService(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping("/user/{id}/experiences")
    public String listExperiences(@PathVariable Integer id, Model model){
       UserDetails userDetails = userDetailsService.getById(id);
        model.addAttribute("experiences", userDetails.getExperiences());
        model.addAttribute("user", userDetails);
        return "admin/experiences/experiencedetails";
    }

    @RequestMapping("/user/{userId}/experience/new")
    public String addExperience(@PathVariable Integer userId, Model model){
        Experience experience = new Experience();
        experience.setUserDetails(userDetailsService.getById(userId));
        model.addAttribute("experience", experience);
        return "admin/experiences/experienceform";
    }

    @RequestMapping("experience/edit/{id}")
    public String editExprience(@PathVariable Integer id, Model model){
        model.addAttribute("experience", experienceService.getById(id));
        return "admin/experiences/experienceform";
    }

    @RequestMapping(value = "/experience", method = RequestMethod.POST)
    public String saveExperience(@Valid Experience experience, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            for (ObjectError objectError : bindingResult.getAllErrors()){
                System.out.println(objectError.toString());
            }
            return "admin/experiences/experienceform";
        }

        experienceService.save(experience);
        return "redirect:/user/" + experience.getUserDetails().getId() + "/experiences";
    }

    @RequestMapping("/experience/delete/{id}")
    public String deleteExperience(@PathVariable Integer id){

        UserDetails userDetails = experienceService.getById(id).getUserDetails();
        experienceService.delete(id);
        return "redirect:/user/" + userDetails.getId() + "/experiences";

    }
}
