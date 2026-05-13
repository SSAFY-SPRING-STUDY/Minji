package com.ssafy.study.spring_project.post.controller.dto;

public class PostRequest {

    private String title;
    private String content;
    private String author;

    public PostRequest(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }
}
