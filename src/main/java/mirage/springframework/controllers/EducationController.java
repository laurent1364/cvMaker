package mirage.springframework.controllers;

import mirage.springframework.domain.Education;
import mirage.springframework.domain.UserDetails;
import mirage.springframework.services.data.EducationService;
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
public class EducationController {

    private EducationService educationService;
    private UserDetailsService userDetailsService;

    @Autowired
    public void setEducationService(EducationService educationService) {
        this.educationService = educationService;
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping("/user/{id}/educations")
    public String listEducations(@PathVariable Integer id, Model model){
        UserDetails userDetails = userDetailsService.getById(id);
        model.addAttribute("educations", userDetails.getEducations());
        model.addAttribute("user", userDetails);
        return "admin/educations/educationdetails";
    }

    @RequestMapping("/user/{id}/education/new")
    public String newEducation(@PathVariable Integer id, Model model){
        Education education = new Education();
       education.setUserDetails(userDetailsService.getById(id));
        model.addAttribute("education", education);
        return "admin/educations/educationform";
    }

    @RequestMapping("/education/edit/{id}")
    public String editEducation(@PathVariable Integer id, Model model){
        model.addAttribute("education", educationService.getById(id));
        return "admin/educations/educationform";
    }

    @RequestMapping(value = "/education", method = RequestMethod.POST)
    public String saveEducation(@Valid Education education, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            for(ObjectError objectError : bindingResult.getAllErrors()){
                System.out.println(objectError.toString());
            }
            return "admin/educations/educationform";
        }

        educationService.save(education);
        return "redirect:/user/" + education.getUserDetails().getId() + "/educations";
    }

    @RequestMapping("/education/delete/{id}")
    public String deleteEducation(@PathVariable Integer id){
        UserDetails userDetails = educationService.getById(id).getUserDetails();
        educationService.delete(id);
        return "redirect:/user/" + userDetails.getId() + "/educations";
    }
}
