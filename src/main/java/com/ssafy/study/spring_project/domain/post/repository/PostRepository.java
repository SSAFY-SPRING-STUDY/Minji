package com.ssafy.study.spring_project.domain.post.repository;

import com.ssafy.study.spring_project.domain.post.entity.PostEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {

    List<PostEntity> PostStore = new ArrayList<>();

    public PostEntity save(PostEntity postEntity) {
        PostStore.add(postEntity);
        return postEntity;
    }

    public Optional<PostEntity> findById(Long postId) {
        for (PostEntity postEntity : PostStore) {
            if (postEntity.getId().equals(postId)) {
                return Optional.of(postEntity);
            }
        }
        return Optional.empty();
    }

    public List<PostEntity> findAll() {
        return new ArrayList<>(PostStore);
    }

    public void deleteById(Long postId) {
        PostStore.removeIf(postEntity -> postEntity.getId().equals(postId));
    }
}
