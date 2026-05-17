package com.ssafy.study.spring_project.member.repository;

import com.ssafy.study.spring_project.member.entity.MemberEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemberRepository {
    // 회원 ID를 key로 회원 Entity를 저장하는 인메모리 저장소
    ConcurrentHashMap<Long, MemberEntity> memberMap = new ConcurrentHashMap<>();

    // 회원가입 시 생성된 회원 Entity를 저장
    public MemberEntity save(MemberEntity member){
        memberMap.put(member.getId(), member);
        return member;
    }

    // 로그인할 때 username으로 가입된 회원을 찾는다
    public Optional<MemberEntity> findByUsername(String username){
        for(MemberEntity entity : memberMap.values()){
            if(username.equals(entity.getUsername()))
                return Optional.of(entity);
        }
        return Optional.empty();
    }

    // 내 정보 조회 시 세션에서 얻은 memberId로 회원을 찾는다
    public Optional<MemberEntity> findById(Long memberId) {
        return Optional.ofNullable(memberMap.get(memberId));
    }
}
