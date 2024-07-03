package services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import controllers.BlogController;
import models.BlogModel;
import repository.BlogRepository;

@Service
public class BlogService 
{
	private static final Logger log = LogManager.getLogger(BlogService.class);
    @Autowired
    private BlogRepository blogRepository;

    public List<BlogModel> getAllBlogs() 
    {
    	log.info("getAllBlogs() method called");
        return blogRepository.findAll();
    }

    public BlogModel getBlogById(Long id) 
    {
    	log.info("getBlogById() method called");
        return blogRepository.findById(id);
    }

    public void saveBlog(BlogModel blog) 
    {
    	log.info("saveBlog() method");
        blogRepository.save(blog);
    }

    public void updateBlog(BlogModel blog) 
    {
    	log.info("updateBlog() method called");
        blogRepository.save(blog);  // Use save() for both create and update
    }

    public void deleteBlog(Long id) 
    {
    	log.info("deleteBlog() method called");
        blogRepository.deleteById(id);
    }
}