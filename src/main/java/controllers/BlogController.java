package controllers;

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
import services.BlogService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    // HTML View Methods
    
    @GetMapping("/blogs")
    public String getAllBlogs(Model model) 
    {
        List<BlogModel> blogs = blogService.getAllBlogs();
        model.addAttribute("blogs", blogs);
        return "blogs";
    }

    @GetMapping("/blogs/{id}")
    public String getBlogById(@PathVariable Long id, Model model) 
    {
        BlogModel blog = blogService.getBlogById(id);
        model.addAttribute("blog", blog);
        return "blogDetail";
    }
    
    @GetMapping("/createBlog")
    public String showCreateBlogForm(HttpSession session, Model model)
    {
        UserModel loggedInUser = (UserModel) session.getAttribute("loggedInUser");
        if (loggedInUser == null)
        {
        	model.addAttribute("errorMessage", "You must be logged in to post a new blog");
            return "redirect:/";
        }

        model.addAttribute("blog", new BlogModel());
        return "createBlog";
    }

    @PostMapping("/createBlog")
    public String createBlog(@ModelAttribute BlogModel blog, HttpSession session)
    {
        UserModel loggedInUser = (UserModel) session.getAttribute("loggedInUser");
        if (loggedInUser == null)
        {
            return "redirect:/";
        }

        blog.setUserId(loggedInUser.getId());
        blog.setCreatedAt(LocalDateTime.now());
        blog.setUpdatedAt(LocalDateTime.now());
        blogService.saveBlog(blog);
        return "redirect:/blogs";
    }
    
    @PostMapping("/blogs/{id}/delete")
    public String deleteBlogById(@PathVariable Long id)
    {
        blogService.deleteBlog(id);
        return "redirect:/blogs";
    }
    
    // REST API Methods
    
    @GetMapping("/api/blogs")
    @ResponseBody
    public List<BlogModel> getAllBlogsApi() {
        return blogService.getAllBlogs();
    }
    
}