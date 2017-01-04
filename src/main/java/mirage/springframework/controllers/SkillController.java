package mirage.springframework.controllers;

import mirage.springframework.domain.Domain;
import mirage.springframework.domain.Skill;
import mirage.springframework.domain.UserDetails;
import mirage.springframework.services.data.DomainService;
import mirage.springframework.services.data.SkillService;
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
 * Created by Mirage on 03/01/2017.
 */
@Controller
public class SkillController {

    private SkillService skillService;

    private DomainService domainService;

    @Autowired
    public void setSkillService(SkillService skillService) {
        this.skillService = skillService;
    }

    @Autowired
    public void setDomainService(DomainService domainService) {
        this.domainService = domainService;
    }

    @RequestMapping("/domains/{id}/skills")
    public String listSkills(@PathVariable Integer id, Model model){
        Domain domain = domainService.getById(id);
        model.addAttribute("skills", domain.getSkills());
        model.addAttribute("domain", domain);
        return "admin/skills/skilldetails";
    }

    @RequestMapping("/domains/{id}/skill/new")
    public String newSkill(@PathVariable Integer id, Model model){
        Skill skill = new Skill();
        skill.setDomain(domainService.getById(id));
        model.addAttribute("skill", skill);
        return "admin/skills/skillform";
    }

    @RequestMapping("/skill/edit/{id}")
    public String editSkill(@PathVariable Integer id, Model model){
        model.addAttribute("skill", skillService.getById(id));
        return "admin/skills/skillform";
    }

    @RequestMapping(value = "/skills", method = RequestMethod.POST)
    public String saveSkill(@Valid Skill skill, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            for(ObjectError objectError : bindingResult.getAllErrors()){
                System.out.println(objectError.toString());
            }
            return "admin/skills/skillform";
        }

        skillService.save(skill);
        return "redirect:/domains/" + skill.getDomain().getId() + "/skills";
    }

    @RequestMapping("/skill/delete/{id}")
    public String deleteSkill(@PathVariable Integer id){
        Domain domain = skillService.getById(id).getDomain();
        skillService.delete(id);
        return "redirect:/domains/" + domain.getId() + "/skills";
    }
}
