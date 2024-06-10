package repository;

import models.UserModel;
import mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository
{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(UserModel user)
    {
        String sql = "INSERT INTO users (username, email, password, created_at) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getEmail(), user.getPassword(), user.getCreatedAt());
    }
    
    public UserModel findByEmail(String email)
    {
        String sql = "SELECT * FROM users WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{email}, new UserMapper());
    }
    
    public UserModel findByUsername(String username)
    {
    	String sql = "SELECT * FROM users WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{username}, new UserMapper());
    }
}
