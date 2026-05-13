package com.ssafy.study.spring_project.post.entity;

import lombok.Getter;

@Getter
public class PostEntity {

    private Long id;
    private String title;
    private String content;
    private String author;

    private static long AUTO_INCREMENT_ID = 1L;

    public PostEntity(String title, String content, String author) {
        this.id = AUTO_INCREMENT_ID++;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
