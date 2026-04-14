package org.example.springstudy.service;

import lombok.RequiredArgsConstructor;
import org.example.springstudy.entity.PostEntity;
import org.example.springstudy.repository.PostRepository;
import org.example.springstudy.dto.PostRequest;
import org.example.springstudy.dto.PostResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public PostResponse save(PostRequest request){
        PostEntity entity = PostRequest.toEntity(request);
        PostEntity savedEntity = postRepository.save(entity);

        PostResponse response = PostResponse.fromEntity(savedEntity);

        return response;
    }

    public List<PostResponse> findAll(){
        List<PostEntity> entityList = postRepository.findAll();
        List<PostResponse> responseList = new ArrayList<>();

        for(PostEntity entity : entityList){
            PostResponse response = PostResponse.fromEntity(entity);
            responseList.add(response);
        }
        return responseList;
    }

    public PostResponse findById(Long id) {
        PostEntity foundEntity = postRepository.findById(id).orElseThrow(() -> new RuntimeException("ID값에 맞는 게시물이 존재하지 않습니다."));
        PostResponse response = PostResponse.fromEntity(foundEntity);

        return response;
    }

    public void update(Long id, PostRequest request){
        Optional<PostEntity> entity = Optional.of(postRepository.findById(id).orElseThrow(() -> new RuntimeException("id값에 맞는 게시물이 존재하지 않습니다.")));

        entity.get().modify(request.getTitle(), request.getContent());

    }

    public void delete(Long id){
        postRepository.deleteById(id);
    }
}
