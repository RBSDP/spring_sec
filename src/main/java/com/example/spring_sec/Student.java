package com.example.spring_sec;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {


    int id;
    String name;
    String stack;
}
