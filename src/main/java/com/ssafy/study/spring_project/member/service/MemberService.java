package com.ssafy.study.spring_project.member.service;

import com.ssafy.study.spring_project.member.controller.dto.MemberRequest;
import com.ssafy.study.spring_project.member.controller.dto.MemberResponse;
import com.ssafy.study.spring_project.member.entity.MemberEntity;
import com.ssafy.study.spring_project.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // 회원가입: 요청 DTO를 Entity로 변환하고 저장한 뒤, 응답 DTO로 바꿔 반환
    public MemberResponse join(MemberRequest request) {
        //1. request를 entity형식으로 변환
        MemberEntity memberEntity = request.toEntity();
        //2. entity를 repository에 저장
        MemberEntity savedEntity = memberRepository.save(memberEntity);
        //3. 저장된 entity를 response로 변환해서 반환
        return MemberResponse.from(savedEntity);
    }

    // 세션에서 얻은 memberId로 회원 정보를 조회한다
    public MemberResponse getMemberInfo(Long memberId){
        //회원 정보 조회, 없으면 ResponseStatusException(404)
        MemberEntity memberEntity = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "회원 정보를 찾을 수 없습니다."));

        return MemberResponse.from(memberEntity);
    }

}
