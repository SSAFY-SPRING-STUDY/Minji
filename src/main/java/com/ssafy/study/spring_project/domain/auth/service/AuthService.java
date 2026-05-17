package com.ssafy.study.spring_project.domain.service;

import com.ssafy.study.spring_project.domain.component.SessionManager;
import com.ssafy.study.spring_project.domain.controller.dto.LoginRequest;
import com.ssafy.study.spring_project.domain.controller.dto.LoginResponse;
import com.ssafy.study.spring_project.domain.member.entity.MemberEntity;
import com.ssafy.study.spring_project.domain.member.repository.MemberRepository;
import com.ssafy.study.spring_project.global.exception.CustomException;
import com.ssafy.study.spring_project.global.exception.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

    // 로그인: username으로 회원을 찾고, 비밀번호가 맞으면 서버에 세션을 생성
    public LoginResponse login(LoginRequest request){
        // username이 없으면 로그인 실패이므로 401 반환
        MemberEntity entity = memberRepository.findByUsername(request.username())
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_USERNAME));

        // 비밀번호가 틀려도 같은 401을 반환
        if (!entity.checkPassword(request.password())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        // 세션 저장소에 "랜덤 세션키 -> 회원 ID" 형태로 저장하고, 세션키를 응답으로 반환
        String token = sessionManager.createSession(entity.getId());
        return LoginResponse.from(token);
    }

    // 로그아웃: 클라이언트가 보낸 세션키를 서버 세션 저장소에서 제거
    public void logout(String sessionKey) {
        sessionManager.removeSession(sessionKey);
    }
}
