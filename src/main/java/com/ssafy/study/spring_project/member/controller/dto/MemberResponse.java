package com.ssafy.study.spring_project.member.controller.dto;

import com.ssafy.study.spring_project.member.entity.MemberEntity;

public record MemberResponse(Long id, String username, String nickname) {
    public static MemberResponse from(MemberEntity member) {
        return new MemberResponse(member.getId(), member.getUsername(), member.getNickname());
    }

}
