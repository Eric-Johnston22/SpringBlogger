package services;

import models.UserModel;
import repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
{

    @Autowired
    private UserRepository userRepository;

    public void saveUser(UserModel user)
    {
        userRepository.save(user);
    }
    
    public boolean validateUser(String email, String password)
    {
        UserModel user = userRepository.findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }

    public UserModel findUserByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }
}
