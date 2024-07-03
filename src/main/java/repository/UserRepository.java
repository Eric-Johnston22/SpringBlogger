package repository;

import models.UserModel;
import mapper.UserMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import controllers.HomeController;

@Repository
public class UserRepository
{
	private static final Logger log = LogManager.getLogger(UserRepository.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(UserModel user)
    {
    	log.info("Saving user: {} to database", user.getUsername());
        String sql = "INSERT INTO users (username, email, password, created_at) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getEmail(), user.getPassword(), user.getCreatedAt());
    }
    
    public UserModel findByEmail(String email)
    {
    	log.info("Searching for email: {} in database", email);
        String sql = "SELECT * FROM users WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{email}, new UserMapper());
    }
    
    public UserModel findByUsername(String username)
    {
    	log.info("Searching for username: {} in database", username);
    	String sql = "SELECT * FROM users WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{username}, new UserMapper());
    }
}
