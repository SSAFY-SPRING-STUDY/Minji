package com.ssafy.study.spring_project.member.controller;

import com.ssafy.study.spring_project.domain.auth.component.SessionManager;
import com.ssafy.study.spring_project.domain.auth.util.AuthTokenUtils;
import com.ssafy.study.spring_project.member.controller.dto.MemberRequest;
import com.ssafy.study.spring_project.member.controller.dto.MemberResponse;
import com.ssafy.study.spring_project.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final SessionManager sessionManager;

    // 회원가입 요청을 받아 회원을 저장하고, 생성된 회원 정보를 201 Created로 반환
    @PostMapping("/api/members")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResponse join(@RequestBody MemberRequest request){
        return memberService.join(request);
    }

    // 내 정보 조회: Authorization 헤더의 Bearer 토큰으로 현재 로그인한 회원을 찾는다
    @GetMapping("/api/members/me")
    public MemberResponse getMyInfo(@RequestHeader("Authorization") String bearerToken){
        // Bearer 형식이 아니면 세션키를 꺼낼 수 없으므로 401 반환
        if (AuthTokenUtils.isValidBearerToken(bearerToken)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다.");
        }

        // 헤더에서 세션키를 꺼낸 뒤, 서버 세션 저장소에서 memberId를 조회
        String sessionKey = AuthTokenUtils.parseBearerToken(bearerToken);
        Long memberId = sessionManager.getMemberId(sessionKey);

        // 세션에서 찾은 memberId로 실제 회원 정보를 조회해서 반환
        return memberService.getMemberInfo(memberId);
    }
}
