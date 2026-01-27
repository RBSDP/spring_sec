package com.example.spring_sec.controller;

import com.example.spring_sec.Student;
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

//     for get mapping CSRF token is not needed
     @GetMapping("students")
     public List<Student> getStudents(){
         return students;
     }

     public void addStudent(@RequestBody Student student){
         students.add(student);

     }

}
