package org.example.springstudy.dto;

import lombok.Getter;
import org.example.springstudy.entity.PostEntity;

@Getter
public class  PostRequest {
    private final String title;
    private final String content;
    private final String author;

    public PostRequest(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public static PostEntity toEntity(PostRequest postRequest){
        return new PostEntity(postRequest.getTitle(), postRequest.getContent(), postRequest.getAuthor());
    }
}
