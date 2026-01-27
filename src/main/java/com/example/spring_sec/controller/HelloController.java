package com.example.spring_sec.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

    // if we add spring security to out application we need to login first
    // once we logged in a session is creataed with a session id
    // login user id : user, password is generated once we run the application
    // we can also set out custom username and password and it should be done in application properties
    // spring.security.user.name = admin
    // spring.security.user.password = 123456
    // this setting user and password is only for learning not for production
    //untill we logout again we can access all the routes without loggin in
    // we can get that session id by using request.getSession().getId(), where request is HttpServletRequest type

    @GetMapping("/")
    public String hello(HttpServletRequest request){
        return "server is running" + request.getSession().getId();
    }

    @GetMapping("/about")
    public String about(HttpServletRequest request){
        return "server is running"+ request.getSession().getId();
    }

    
}