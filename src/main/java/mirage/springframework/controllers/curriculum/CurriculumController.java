package mirage.springframework.controllers.curriculum;

import mirage.springframework.domain.Curriculum;
import mirage.springframework.domain.UserDetails;
import mirage.springframework.services.data.CurriculumService;
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
 * Created by Mirage on 18/12/2016.
 */
@Controller
public class CurriculumController {

    private CurriculumService curriculumService;
    private UserDetailsService userDetailsService;

    @Autowired
    public void setCurriculumService(CurriculumService curriculumService) {
        this.curriculumService = curriculumService;
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping("/user/{id}/curriculum/new")
    public String newCurriculum(@PathVariable Integer id, Model model){
        Curriculum curriculum = new Curriculum();
        curriculum.setUserDetails(userDetailsService.getById(id));
        model.addAttribute("curriculum", curriculum);
        return "admin/curriculums/curriculumform";
    }

    @RequestMapping("/curriculum/edit/{id}")
    public String editCurriculum(@PathVariable Integer id, Model model){
        model.addAttribute("curriculum", curriculumService.getById(id));
        return "admin/curriculums/curriculumform";
    }

    @RequestMapping(value = "/curriculums", method = RequestMethod.POST)
    public String addCurriculum(@Valid Curriculum curriculum, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            for(ObjectError objectError : bindingResult.getAllErrors()){
                System.out.println(objectError.toString());
            }
            return "admin/curriculums/curriculumform";
        }
        curriculumService.save(curriculum);

        return "redirect:/user/" + curriculum.getUserDetails().getId();
    }

    @RequestMapping("/curriculum/delete/{id}")
    public String deleteCurriculum(@PathVariable Integer id){
        UserDetails userDetails = curriculumService.getById(id).getUserDetails();
        curriculumService.delete(id);
        return "redirect:/user/" + userDetails.getId();
    }

    @RequestMapping("/curriculum/view/{id}")
    public String viewCurriculum(@PathVariable Integer id, Model model){
        model.addAttribute("curriculum", curriculumService.getById(id));
        model.addAttribute("user", curriculumService.listAllNoneElementsByCurriculum(id));
        return "admin/curriculums/curriculumdetails";
    }

    @RequestMapping("/curriculum/{curriculumId}/experience/{experienceId}/add")
    public String addExperience(@PathVariable Integer curriculumId, @PathVariable Integer experienceId){
        curriculumService.addExperience(curriculumId, experienceId);
        return "redirect:/curriculum/view/" + curriculumId;
    }

    @RequestMapping("/curriculum/{curriculumId}/experience/{experienceId}/remove")
    public String removeExperience(@PathVariable Integer curriculumId, @PathVariable Integer experienceId){
        curriculumService.removeExperience(curriculumId, experienceId);
        return "redirect:/curriculum/view/" + curriculumId;
    }

    @RequestMapping("/curriculum/{curriculumId}/education/{educationId}/add")
    public String addEducation(@PathVariable Integer curriculumId, @PathVariable Integer educationId){
        curriculumService.addEducation(curriculumId, educationId);
        return "redirect:/curriculum/view/" + curriculumId;
    }

    @RequestMapping("/curriculum/{curriculumId}/education/{educationId}/remove")
    public String removeEducation(@PathVariable Integer curriculumId, @PathVariable Integer educationId){
        curriculumService.removeEducation(curriculumId, educationId);
        return "redirect:/curriculum/view/" + curriculumId;
    }

    @RequestMapping("/curriculum/{curriculumId}/domain/{domainId}/add")
    public String addDomain(@PathVariable Integer curriculumId, @PathVariable Integer domainId){
        curriculumService.addDomain(curriculumId, domainId);
        return "redirect:/curriculum/view/" + curriculumId;
    }

    @RequestMapping("/curriculum/{curriculumId}/domain/{domainId}/remove")
    public String removeDomain(@PathVariable Integer curriculumId, @PathVariable Integer domainId){
        curriculumService.removeDomain(curriculumId, domainId);
        return "redirect:/curriculum/view/" + curriculumId;
    }

    @RequestMapping("/curriculum/{curriculumId}/referee/{refereeId}/add")
    public String addReferee(@PathVariable Integer curriculumId, @PathVariable Integer refereeId){
        curriculumService.addReferee(curriculumId, refereeId);
        return "redirect:/curriculum/view/" + curriculumId;
    }

    @RequestMapping("/curriculum/{curriculumId}/referee/{refereeId}/remove")
    public String removeReferee(@PathVariable Integer curriculumId, @PathVariable Integer refereeId){
        curriculumService.removeReferee(curriculumId, refereeId);
        return "redirect:/curriculum/view/" + curriculumId;
    }
}
