package org.example.springstudy.repository;

import org.example.springstudy.dto.PostRequest;
import org.example.springstudy.entity.PostEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {
    List<PostEntity> postList = new ArrayList<>();

    public PostEntity save(PostEntity entity) {
        postList.add(entity);

        return entity;
    }

    public List<PostEntity> findAll() {
        return postList;
    }

    public Optional<PostEntity> findById(Long id) {
        for (PostEntity entity : postList) {
            if (entity.getId().equals(id)) {
                return Optional.of(entity);
            }
        }
        return Optional.empty();
    }

    public void update(Long id, PostRequest request) {
        Optional<PostEntity> entity = findById(id);

        if (entity.isPresent())
            entity.get().modify(request.getTitle(), request.getContent());
    }

    public void deleteById(Long id) {
        Optional<PostEntity> entity = findById(id);

        if (entity.isPresent())
            postList.remove(entity.get());
    }
}

