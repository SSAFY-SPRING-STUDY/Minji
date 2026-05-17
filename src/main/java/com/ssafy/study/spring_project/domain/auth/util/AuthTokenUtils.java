package com.ssafy.study.spring_project.domain.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthTokenUtils {

    // 클라이언트는 Authorization 헤더에 "Bearer {세션키}" 형태로 토큰을 보낸다
    public static final String PREFIX_BEARER = "Bearer ";

    // 이름은 isValid지만 현재 로직은 "유효하지 않은 Bearer 토큰이면 true"를 반환한다
    public static boolean isValidBearerToken(String bearerToken) {
        return bearerToken == null || !bearerToken.startsWith(PREFIX_BEARER);
    }

    // "Bearer " 접두사를 제거해서 SessionManager가 저장한 실제 세션키만 꺼낸다
    public static String parseBearerToken(String bearerToken) {
        return bearerToken.substring(PREFIX_BEARER.length());
    }
}
