package com.ssafy.study.spring_project.domain.auth.controller.dto;

public record LoginRequest(
        String username,
        String password
) {

}
