package com.ssafy.study.spring_project.domain.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String nickname;


    // create() 메서드로만 객체를 만들게 하려고 생성자는 private으로 둔다
    private MemberEntity(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    //객체를 만들기 전에는 아직 MemberEntity가 없으므로 클래스 이름으로 바로 호출할 수 있게 static 사용
    public static MemberEntity create(String username, String password, String nickname) {
        return  new MemberEntity(username, password, nickname);
    }

    // 로그인 시 입력한 비밀번호가 회원가입 때 저장된 비밀번호와 같은지 확인
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}
