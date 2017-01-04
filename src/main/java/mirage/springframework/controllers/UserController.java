package mirage.springframework.controllers;

import mirage.springframework.domain.User;
import mirage.springframework.services.data.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Mirage on 16/12/2016.
 */
@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/users")
    public String users(Model model){
        model.addAttribute("users", userService.listAll());
        return "admin/users/users";
    }

    @RequestMapping("/user/new")
    public String addUser(Model model){
        model.addAttribute("user", new User());
        return "admin/users/userform";
    }
    @RequestMapping("/user/edit/{id}")
    public String editUser(@PathVariable Integer id, Model model){
        model.addAttribute("user", userService.getById(id));
        return "admin/users/userform";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "admin/users/userform";
        }

        User userSaved = userService.save(user);
        return "redirect:/user/" + userSaved.getUserDetails().getId();
    }

    @RequestMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Integer id){
        userService.delete(id);
        return "redirect:/users";
    }

}
