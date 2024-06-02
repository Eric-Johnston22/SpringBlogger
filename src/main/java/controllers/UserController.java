package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import models.UserModel;
import services.UserService;

@Controller
public class UserController 
{

    @Autowired
    private UserService userService;

    @GetMapping("/createAccount")
    public String creatAccount(Model model) 
    {
    	model.addAttribute("user", new UserModel());
        return "createAccount";
    }

    @PostMapping("/createAccount")
    public String createAccount(@ModelAttribute UserModel user) 
    {
        userService.saveUser(user);
        return "redirect:/";
    }
}
