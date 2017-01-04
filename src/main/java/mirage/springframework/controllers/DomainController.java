package mirage.springframework.controllers;

import mirage.springframework.domain.Domain;
import mirage.springframework.domain.Education;
import mirage.springframework.domain.UserDetails;
import mirage.springframework.services.data.DomainService;
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
 * Created by Mirage on 03/01/2017.
 */
@Controller
public class DomainController {

    private DomainService domainService;
    private UserDetailsService userDetailsService;

    @Autowired
    public void setDomainService(DomainService domainService) {
        this.domainService = domainService;
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @RequestMapping("/user/{id}/domains")
    public String listDomains(@PathVariable Integer id, Model model){
        UserDetails userDetails = userDetailsService.getById(id);
        model.addAttribute("domains", userDetails.getDomains());
        model.addAttribute("user", userDetails);
        return "admin/domains/domaindetails";
    }

    @RequestMapping("/user/{id}/domain/new")
    public String newDomain(@PathVariable Integer id, Model model){
        Domain domain = new Domain();
        domain.setUserDetails(userDetailsService.getById(id));
        model.addAttribute("domain", domain);
        return "admin/domains/domainform";
    }

    @RequestMapping("/domain/edit/{id}")
    public String editDomain(@PathVariable Integer id, Model model){
        model.addAttribute("domain", domainService.getById(id));
        return "admin/domains/domainform";
    }

    @RequestMapping(value = "/domains", method = RequestMethod.POST)
    public String saveDomain(@Valid Domain domain, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            for(ObjectError objectError : bindingResult.getAllErrors()){
                System.out.println(objectError.toString());
            }
            return "admin/domains/domainform";
        }

        domainService.save(domain);
        return "redirect:/user/" + domain.getUserDetails().getId() + "/domains";
    }

    @RequestMapping("/domain/delete/{id}")
    public String deleteDomain(@PathVariable Integer id){
        UserDetails userDetails = domainService.getById(id).getUserDetails();
        domainService.delete(id);
        return "redirect:/user/" + userDetails.getId() + "/domains";
    }

}
