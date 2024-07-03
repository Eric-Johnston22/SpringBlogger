package repository;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import models.BlogModel;
import mapper.BlogMapper;

import java.util.List;

@Repository
public class BlogRepository {

	private static final Logger log = LogManager.getLogger(BlogRepository.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<BlogModel> findAll() 
    {
        String sql = "SELECT * FROM blog";
        log.info("findAll() method called");
        return jdbcTemplate.query(sql, new BlogMapper());
    }
    
    public BlogModel findById(Long id) 
    {
    	log.info("Finding blog by id: {}", id);
        String sql = "SELECT * FROM blog WHERE blog_id = ?";
        return jdbcTemplate.queryForObject(sql, new BlogMapper(), id);
    }

    public void save(BlogModel blog)
    {
    	log.info("Saving blog post {} to database", blog.getId());
        String sql = "INSERT INTO blog (user_id, title, content, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, blog.getUserId(), blog.getTitle(), blog.getContent(), blog.getCreatedAt(), blog.getUpdatedAt());
    }

    public void update(BlogModel blog) 
    {
    	log.info("Updating blog post {}", blog.getId());
        String sql = "UPDATE blog SET title = ?, content = ?, updated_at = ? WHERE blog_id = ?";
        jdbcTemplate.update(sql, blog.getTitle(), blog.getContent(), blog.getUpdatedAt(), blog.getId());
    }

    public void deleteById(Long id) 
    {
    	log.info("Deleting blog post {} from database", id);
        String sql = "DELETE FROM blog WHERE blog_id = ?";
        jdbcTemplate.update(sql, id);
    }

}
