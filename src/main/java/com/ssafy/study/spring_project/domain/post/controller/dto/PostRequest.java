package com.ssafy.study.spring_project.domain.post.controller.dto;

import com.ssafy.study.spring_project.domain.member.entity.MemberEntity;
import com.ssafy.study.spring_project.domain.post.entity.PostEntity;

public class PostRequest {

    private String title;
    private String content;

    public PostRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public PostEntity toEntity(MemberEntity author) {
        return PostEntity.create(title, content, author);
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

}
