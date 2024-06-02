package repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import models.BlogModel;
import mapper.BlogMapper;

import java.util.List;

@Repository
public class BlogRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<BlogModel> findAll() 
    {
        String sql = "SELECT * FROM Blog";
        return jdbcTemplate.query(sql, new BlogMapper());
    }
    
    public BlogModel findById(Long id) 
    {
        String sql = "SELECT * FROM Blog WHERE blog_id = ?";
        return jdbcTemplate.queryForObject(sql, new BlogMapper(), id);
    }

    public void save(BlogModel blog)
    {
        String sql = "INSERT INTO Blog (user_id, title, content, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, blog.getUserId(), blog.getTitle(), blog.getContent(), blog.getCreatedAt(), blog.getUpdatedAt());
    }

    public void update(BlogModel blog) 
    {
        String sql = "UPDATE Blog SET title = ?, content = ?, updated_at = ? WHERE blog_id = ?";
        jdbcTemplate.update(sql, blog.getTitle(), blog.getContent(), blog.getUpdatedAt(), blog.getId());
    }

    public void deleteById(Long id) 
    {
        String sql = "DELETE FROM Blog WHERE blog_id = ?";
        jdbcTemplate.update(sql, id);
    }

}
