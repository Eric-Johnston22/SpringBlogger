package controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import models.BlogModel;
import models.UserModel;
import repository.UserRepository;
import services.BlogService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class BlogController {

	private static final Logger log = LogManager.getLogger(BlogController.class);
    @Autowired
    private BlogService blogService;

    // HTML View Methods
    
    @GetMapping("/blogs")
    public String getAllBlogs(Model model) 
    {
    	log.info("/blogs GET request called");
        List<BlogModel> blogs = blogService.getAllBlogs();
        model.addAttribute("blogs", blogs);
        return "blogs";
    }

    @GetMapping("/blogs/{id}")
    public String getBlogById(@PathVariable Long id, Model model) 
    {
    	log.info("/blogs/{id} GET request called");
        BlogModel blog = blogService.getBlogById(id);
        model.addAttribute("blog", blog);
        return "blogDetail";
    }
    
    @GetMapping("/createBlog")
    public String showCreateBlogForm(HttpSession session, Model model)
    {
    	log.info("/createBlog GET request called");
        UserModel loggedInUser = (UserModel) session.getAttribute("loggedInUser");
        if (loggedInUser == null)
        {
        	log.error("user is not logged in");
        	model.addAttribute("errorMessage", "You must be logged in to post a new blog");
            return "redirect:/";
        }
        
        model.addAttribute("blog", new BlogModel());
        return "createBlog";
    }

    @PostMapping("/createBlog")
    public String createBlog(@ModelAttribute BlogModel blog, HttpSession session)
    {
    	log.info("/createBlog POST request called");
        UserModel loggedInUser = (UserModel) session.getAttribute("loggedInUser");
        if (loggedInUser == null)
        {
        	log.error("user is not logged in. users must be logged in to create blogs");
            return "redirect:/";
        }

        blog.setUserId(loggedInUser.getId());
        blog.setCreatedAt(LocalDateTime.now());
        blog.setUpdatedAt(LocalDateTime.now());
        blogService.saveBlog(blog);
        return "redirect:/blogs";
    }
    
    @PostMapping("/blogs/{id}/delete")
    public String deleteBlogById(@PathVariable Long id, HttpSession session)
    {
    	log.info("/blogs/{id}/delete POST request called");
    	
    	UserModel loggedInUser = (UserModel) session.getAttribute("loggedInUser");
        if (loggedInUser == null)
        {
        	log.error("user is not logged in. users must be logged in to delete blogs.");
            return "redirect:/blogs/{id}";
        }
    	
        blogService.deleteBlog(id);
        return "redirect:/blogs";
    }
    
    // REST API Methods
    
    @GetMapping("/api/blogs")
    @ResponseBody
    public List<BlogModel> getAllBlogsApi() 
    {
    	log.info("/api/blogs GET request called");
        return blogService.getAllBlogs();
    }
    
}