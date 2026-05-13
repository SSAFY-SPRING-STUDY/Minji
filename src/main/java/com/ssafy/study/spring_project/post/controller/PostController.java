package com.ssafy.study.spring_project.post.controller;

import com.ssafy.study.spring_project.post.controller.dto.PostRequest;
import com.ssafy.study.spring_project.post.controller.dto.PostResponse;
import com.ssafy.study.spring_project.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/api/posts")
    @ResponseStatus(HttpStatus.CREATED)
    //RequestBody: JSON 형태를 Request 형태로 변환
    public PostResponse createPost(@RequestBody PostRequest postRequest) {
        return postService.save(postRequest);
    }

    @GetMapping("/api/posts")
    public List<PostResponse> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/api/posts/{postId}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long postId) {
        try {
            return ResponseEntity.ok(postService.findById(postId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/api/posts/{postId}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable Long postId, @RequestBody PostRequest postRequest) {
        try {
            return ResponseEntity.ok(postService.update(postRequest, postId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/posts/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        try {
            postService.delete(postId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
