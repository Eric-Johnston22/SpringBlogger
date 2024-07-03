package controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	private static final Logger log = LogManager.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @GetMapping("/createAccount")
    public String creatAccount(Model model) 
    {
    	log.info("/createAccount GET request called");
    	model.addAttribute("user", new UserModel());
        return "createAccount";
    }

    @PostMapping("/createAccount")
    public String createAccount(@ModelAttribute UserModel user) 
    {
    	log.info("/createAccount POST request called");
        userService.saveUser(user);
        return "redirect:/";
    }
}
