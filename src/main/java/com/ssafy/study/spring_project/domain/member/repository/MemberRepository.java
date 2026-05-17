package com.ssafy.study.spring_project.domain.member.repository;

import com.ssafy.study.spring_project.domain.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByUsername(String username);
}
