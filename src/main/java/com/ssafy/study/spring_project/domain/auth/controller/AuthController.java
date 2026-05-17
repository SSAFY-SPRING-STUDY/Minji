package com.ssafy.study.spring_project.domain.auth.controller;

import com.ssafy.study.spring_project.domain.auth.controller.dto.LoginRequest;
import com.ssafy.study.spring_project.domain.auth.controller.dto.LoginResponse;
import com.ssafy.study.spring_project.domain.auth.service.AuthService;
import com.ssafy.study.spring_project.domain.auth.util.AuthTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // username/password를 받아 로그인하고, 성공하면 클라이언트가 사용할 세션 토큰을 반환
    @PostMapping("/api/auth/login")
    public LoginResponse login(@RequestBody LoginRequest request){
        return authService.login(request);
    }

    // Authorization 헤더의 Bearer 토큰에서 세션키를 꺼내 서버 세션 저장소에서 삭제
    @PostMapping("/api/auth/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@RequestHeader("Authorization") String bearerToken) {
        // 헤더가 없거나 "Bearer "로 시작하지 않으면 인증 실패
        if (AuthTokenUtils.isValidBearerToken(bearerToken)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다.");
        }

        // "Bearer {세션키}"에서 실제 세션키 부분만 분리
        String sessionKey = AuthTokenUtils.parseBearerToken(bearerToken);
        authService.logout(sessionKey);
    }
}
