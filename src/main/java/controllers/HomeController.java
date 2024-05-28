package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController 
{
	// return a string from a /test1 url
	@GetMapping("/")
	public String home()
	{
		return "index";
	}
	
	@GetMapping("/test2")
	public String showHelloPage()
	{
		return "helloPage";
	}
}
