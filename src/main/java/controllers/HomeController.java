package controllers;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import models.UserModel;
import services.UserService;

@Controller
public class HomeController 
{
	private static final Logger log = LogManager.getLogger(HomeController.class.getName());
	
	@Autowired
    private UserService userService;

	@GetMapping("/")
    public String showHomePage(Model model, HttpSession session)
    {
        if (session.getAttribute("errorMessage") != null)
        {
            model.addAttribute("errorMessage", session.getAttribute("errorMessage"));
            session.removeAttribute("errorMessage");
        }
        log.info("Home page '/' GET request called");
        model.addAttribute("user", new UserModel());
        return "index";
    }
	
	
	@PostMapping("/login")
    public String login(@ModelAttribute UserModel user, HttpSession session, Model model)
    {
		log.info("/login POST request called");
        boolean isValidUser = userService.validateUser(user.getEmail(), user.getPassword());
        if (isValidUser)
        {
            UserModel loggedInUser = userService.findUserByEmail(user.getEmail());
            log.info("User logged in: {}", user.getEmail());
            session.setAttribute("loggedInUser", loggedInUser);
            return "redirect:/blogs";
        }
        else
        {
            model.addAttribute("loginError", true);
            log.warn("Login attempt failed for user: {}", user.getEmail());
            return "index";
        }
    }
	
}
