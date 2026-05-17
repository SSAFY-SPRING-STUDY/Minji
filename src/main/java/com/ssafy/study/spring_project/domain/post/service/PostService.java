package com.ssafy.study.spring_project.domain.post.service;

import com.ssafy.study.spring_project.domain.member.entity.MemberEntity;
import com.ssafy.study.spring_project.domain.member.repository.MemberRepository;
import com.ssafy.study.spring_project.domain.post.controller.dto.PostRequest;
import com.ssafy.study.spring_project.domain.post.controller.dto.PostResponse;
import com.ssafy.study.spring_project.domain.post.entity.PostEntity;
import com.ssafy.study.spring_project.domain.post.repository.PostRepository;
import com.ssafy.study.spring_project.global.exception.CustomException;
import com.ssafy.study.spring_project.global.exception.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public PostResponse create(PostRequest request, Long authorId) {
        MemberEntity author = memberRepository.findById(authorId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        return PostResponse.from(postRepository.save(request.toEntity(author)));
    }

    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream()
                .map(PostResponse::from)
                .collect(Collectors.toList());
    }

    public PostResponse getPostById(Long id) {
        PostEntity postEntity = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        return PostResponse.from(postEntity);
    }

    public PostResponse update(PostRequest request, Long id, Long authorId) {
        MemberEntity author = memberRepository.findById(authorId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        PostEntity post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        if (!post.getAuthor().getId().equals(author.getId())) {
            throw new CustomException(ErrorCode.INVALID_PERMISSION);
        }
        post.update(request.getTitle(), request.getContent());
        return PostResponse.from(post);
    }

    public void delete(Long id, Long authorId) {
        MemberEntity author = memberRepository.findById(authorId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        PostEntity post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        if (!post.getAuthor().getId().equals(author.getId())) {
            throw new CustomException(ErrorCode.INVALID_PERMISSION);
        }
        postRepository.deleteById(id);
    }
}
