
package com.example.spring_sec.service;
import com.example.spring_sec.dto.UserRepo;
import com.example.spring_sec.model.User;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.AutoWired;
@Service
public class UserService {

    @AutoWired
    private UserRepo repo;

    public User saveUser(User user) {
        CryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        repo.save(user);
        return user;
    }
    
}