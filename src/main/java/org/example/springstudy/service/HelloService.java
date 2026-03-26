package org.example.springstudy.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String hi(){
        return "하이요";
    }
}
