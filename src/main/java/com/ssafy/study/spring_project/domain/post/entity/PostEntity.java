package com.ssafy.study.spring_project.domain.post.entity;

import com.ssafy.study.spring_project.domain.member.entity.MemberEntity;
import lombok.Getter;

@Getter
public class PostEntity {

    private Long id;
    private String title;
    private String content;
    private MemberEntity author;

    private static long AUTO_INCREMENT_ID = 1L;

    private PostEntity(Long id, String title, String content, MemberEntity author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public static PostEntity create(String title, String content, MemberEntity author) {
        return new PostEntity(AUTO_INCREMENT_ID++, title, content, author);
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
