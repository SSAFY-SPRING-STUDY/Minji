package com.ssafy.study.spring_project.domain.post.controller.dto;

import com.ssafy.study.spring_project.domain.member.controller.dto.MemberResponse;
import com.ssafy.study.spring_project.domain.post.entity.PostEntity;

public record PostResponse(Long id, String title, String content, MemberResponse memberResponse) {
    public static PostResponse from(PostEntity postEntity) {
        return new PostResponse(
                postEntity.getId(),
                postEntity.getTitle(),
                postEntity.getContent(),
                MemberResponse.from(postEntity.getAuthor())
        );
    }
}
