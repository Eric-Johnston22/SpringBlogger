package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import models.BlogModel;
import repository.BlogRepository;

@Service
public class BlogService 
{

    @Autowired
    private BlogRepository blogRepository;

    public List<BlogModel> getAllBlogs() 
    {
        return blogRepository.findAll();
    }

    public BlogModel getBlogById(Long id) 
    {
        return blogRepository.findById(id);
    }

    public void saveBlog(BlogModel blog) 
    {
        blogRepository.save(blog);
    }

    public void updateBlog(BlogModel blog) 
    {
        blogRepository.save(blog);  // Use save() for both create and update
    }

    public void deleteBlog(Long id) 
    {
        blogRepository.deleteById(id);
    }
}