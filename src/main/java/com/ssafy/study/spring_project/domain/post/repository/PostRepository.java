package com.ssafy.study.spring_project.domain.post.repository;

import com.ssafy.study.spring_project.domain.post.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
