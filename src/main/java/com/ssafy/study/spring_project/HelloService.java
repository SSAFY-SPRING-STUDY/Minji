package com.ssafy.study.spring_project;

import com.ssafy.study.spring_project.member.entity.MemberEntity;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public String hi(){
        return "Hello Spring Boot!";
    }
}
