package org.example.springstudy.controller;

import lombok.RequiredArgsConstructor;
import org.example.springstudy.dto.PostRequest;
import org.example.springstudy.dto.PostResponse;
import org.example.springstudy.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    // 게시글 생성
    @PostMapping()
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest request){
        PostResponse response = postService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping()
    public ResponseEntity<List<PostResponse>> findAllPost(){
        return ResponseEntity.status(HttpStatus.OK).body(postService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> findPostById(@PathVariable long id){
        try {
            PostResponse response = postService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch(RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody PostRequest request) {
        try{
            postService.update(id, request);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id){
        postService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

