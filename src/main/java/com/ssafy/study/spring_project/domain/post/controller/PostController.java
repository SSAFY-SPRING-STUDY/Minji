package com.ssafy.study.spring_project.domain.post.controller;

import com.ssafy.study.spring_project.domain.auth.component.SessionManager;
import com.ssafy.study.spring_project.domain.post.controller.dto.PostRequest;
import com.ssafy.study.spring_project.domain.post.controller.dto.PostResponse;
import com.ssafy.study.spring_project.domain.post.service.PostService;
import com.ssafy.study.spring_project.domain.auth.util.AuthTokenUtils;
import com.ssafy.study.spring_project.global.exception.CustomException;
import com.ssafy.study.spring_project.global.exception.error.ErrorCode;
import com.ssafy.study.spring_project.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;
    private final SessionManager sessionManager;

    @PostMapping
    public ResponseEntity<ApiResponse<PostResponse>> createPost(
            @RequestHeader("Authorization") String bearerToken,
            @RequestBody PostRequest postRequest) {
        if (AuthTokenUtils.isValidBearerToken(bearerToken)) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
        String sessionKey = AuthTokenUtils.parseBearerToken(bearerToken);
        Long authorId = sessionManager.getMemberId(sessionKey);
        PostResponse response = postService.create(postRequest, authorId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("게시글 생성 성공", response));
    }

    @GetMapping
    public ApiResponse<List<PostResponse>> getAllPosts() {
        return ApiResponse.success("게시글 목록 조회 성공", postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ApiResponse<PostResponse> getPostById(@PathVariable Long id) {
        return ApiResponse.success("게시글 조회 성공", postService.getPostById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<PostResponse> updatePost(
            @PathVariable Long id,
            @RequestHeader("Authorization") String bearerToken,
            @RequestBody PostRequest postRequest) {
        if (AuthTokenUtils.isValidBearerToken(bearerToken)) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
        String sessionKey = AuthTokenUtils.parseBearerToken(bearerToken);
        Long authorId = sessionManager.getMemberId(sessionKey);
        return ApiResponse.success("게시글 수정 성공", postService.update(postRequest, id, authorId));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(
            @PathVariable Long id,
            @RequestHeader("Authorization") String bearerToken) {
        if (AuthTokenUtils.isValidBearerToken(bearerToken)) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
        String sessionKey = AuthTokenUtils.parseBearerToken(bearerToken);
        Long authorId = sessionManager.getMemberId(sessionKey);
        postService.delete(id, authorId);
    }
}
