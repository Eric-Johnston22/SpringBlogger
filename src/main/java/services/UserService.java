package services;

import models.UserModel;
import repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService
{

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    public void saveUser(UserModel user)
    {
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    
    public boolean validateUser(String email, String password)
    {
        UserModel user = userRepository.findByEmail(email);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }

    public UserModel findUserByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }
    
    public void findUserByUsername(String username)
    {
    	System.out.println(userRepository.findByUsername(username));
    }
}
