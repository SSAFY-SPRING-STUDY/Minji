package com.ssafy.study.spring_project.post.service;

import com.ssafy.study.spring_project.post.controller.dto.PostRequest;
import com.ssafy.study.spring_project.post.controller.dto.PostResponse;
import com.ssafy.study.spring_project.post.entity.PostEntity;
import com.ssafy.study.spring_project.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    // 요청 DTO를 Entity로 바꿔 저장하고, 응답 DTO로 변환해서 반환
    public PostResponse save(PostRequest postRequest) {
        PostEntity postEntity = new PostEntity(postRequest.getTitle(), postRequest.getContent(), postRequest.getAuthor());
        PostEntity saveEntity = postRepository.save(postEntity);

        return PostResponse.from(saveEntity);
    }

    // 저장소의 모든 게시글 Entity를 조회한 뒤, 응답 DTO 리스트로 변환
    public List<PostResponse> getAllPosts() {
        List<PostEntity> entityList = postRepository.findAll();

        List<PostResponse> tempList = new ArrayList<>();
        for (PostEntity postEntity : entityList) {
            tempList.add(PostResponse.from(postEntity));
        }

        return tempList;
    }

    // ID로 게시글을 조회하고, 없으면 예외를 발생시켜 Controller에서 404로 처리
    public PostResponse findById(Long postId) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        return PostResponse.from(postEntity);
    }

    // ID로 기존 게시글을 찾은 뒤 제목과 내용만 수정
    public PostResponse update(PostRequest request, Long postId) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        postEntity.update(request.getTitle(), request.getContent());

        return PostResponse.from(postEntity);
    }

    // 삭제 전 게시글이 존재하는지 확인하고, 존재하면 저장소에서 삭제
    public void delete(Long postId) {
        postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        postRepository.deleteById(postId);
    }
}
