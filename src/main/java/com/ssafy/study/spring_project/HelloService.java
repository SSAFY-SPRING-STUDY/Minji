package com.ssafy.study.spring_project;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public String hi(){
        return "Hello Spring Boot!";
    }
}
