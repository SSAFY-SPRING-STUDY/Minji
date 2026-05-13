package com.ssafy.study.spring_project.post.repository;

import com.ssafy.study.spring_project.post.entity.PostEntity;
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
                //값이 있을 수도 있고 없을 수도 있을 때 Optional.of 사용
                //찾는 값이 null이 아니면 Optional.of, null이면 Optional.empty 사용
                return Optional.of(postEntity);
            }
        }
        return Optional.empty();
    }

    public List<PostEntity> findAll(){
        return new ArrayList<>(PostStore);
    }

    public void deleteById(Long postId) {
        //PostStore에서 postEntity의 id가 postId와 같은 것을 찾아서 제거
        PostStore.removeIf(postEntity -> postEntity.getId().equals(postId));
    }
}
