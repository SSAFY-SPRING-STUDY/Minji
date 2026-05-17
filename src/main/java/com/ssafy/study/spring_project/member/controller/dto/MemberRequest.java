package com.ssafy.study.spring_project.member.controller.dto;
import com.ssafy.study.spring_project.member.entity.MemberEntity;

public record MemberRequest(String username, String password, String nickname) {
    public MemberEntity toEntity() {
        return MemberEntity.create(username, password, nickname);
    }
}
