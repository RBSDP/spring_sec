package com.example.spring_sec.service;

import com.example.spring_sec.dto.UserRepo;
import com.example.spring_sec.model.User;
import com.example.spring_sec.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username);
        if(user == null){
            System.out.println("user not founf");
            throw new UsernameNotFoundException("User 404");

        }
        return new UserPrincipal(user);
    }
}
