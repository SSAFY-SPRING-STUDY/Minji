package com.ssafy.study.spring_project.domain.auth.component;

import com.ssafy.study.spring_project.global.exception.CustomException;
import com.ssafy.study.spring_project.global.exception.error.ErrorCode;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {
    // 서버 메모리에 세션키와 회원 ID를 저장한다. 예: "uuid..." -> 1L
    Map<String, Long> sessionMap = new ConcurrentHashMap<>();

    // 로그인 성공 시 UUID 세션키를 만들고, 어떤 회원의 세션인지 함께 저장
    public String createSession(Long memberId){
        String token = UUID.randomUUID().toString();
        sessionMap.put(token, memberId);
        return token;
    }

    // 로그아웃 시 세션키가 유효한지 확인한 뒤 서버 저장소에서 제거
    public void removeSession(String sessionKey) {
        if (!sessionMap.containsKey(sessionKey)) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        sessionMap.remove(sessionKey);
    }

    // 인증이 필요한 요청에서 세션키로 로그인한 회원 ID를 찾는다
    public Long getMemberId(String sessionKey) {
        Long memberId = sessionMap.get(sessionKey);
        if (memberId == null) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        return memberId;
    }
}
