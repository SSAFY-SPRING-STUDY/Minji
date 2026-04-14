package org.example.springstudy.entity;

import lombok.Getter;

@Getter
public class PostEntity {

    private static long AUTO_INCREMENT = 1L;

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostEntity(String title, String content, String author) {
        this.id = AUTO_INCREMENT++;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void modify(String title, String content){
        this.title = title;
        this.content = content;
    }
}
