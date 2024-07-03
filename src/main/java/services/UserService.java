package services;

import models.UserModel;
import repository.UserRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import controllers.HomeController;

@Service
public class UserService
{
	private static final Logger log = LogManager.getLogger(UserService.class.getName());
    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    public void saveUser(UserModel user)
    {
    	log.info("saveUser() called");
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    
    public boolean validateUser(String email, String password)
    {
    	log.info("Validating user: {}", email);
        UserModel user = userRepository.findByEmail(email);
        if(user == null)
        {
        	log.warn("No user found with email: {}", email);
        	return false;
        }
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }

    public UserModel findUserByEmail(String email)
    {
    	log.info("findUserByEmail() called");
        return userRepository.findByEmail(email);
    }
    
    public UserModel findUserByUsername(String username)
    {
    	log.info("findUserByUsername() called");
    	System.out.println(userRepository.findByUsername(username));
    	return userRepository.findByUsername(username);
    }
}
