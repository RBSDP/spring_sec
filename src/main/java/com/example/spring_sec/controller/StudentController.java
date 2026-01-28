package com.example.spring_sec.controller;

import com.example.spring_sec.Student;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

     List<Student> students = new ArrayList<>(List.of(
             new Student(1, "prasad","java"),
             new Student(2, "DP","javaScript")
     ));
// Spring Security does NOT automatically send a CSRF token during login.
// It only generates a CSRF token when:

// A view accesses it (Thymeleaf HTML forms)
// A controller accesses it (request.getAttribute("_csrf"))
// A client calls a dedicated endpoint like your /csrf_token endpoint
// A POST/PUT/DELETE happens and no token exists
     @GetMapping("csrf_token")
     public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
     }

//     for get mapping CSRF token is not needed
     @GetMapping("students")
     public List<Student> getStudents(){
         return students;
     }

     public void addStudent(@RequestBody Student student){
         students.add(student);

     }

}
