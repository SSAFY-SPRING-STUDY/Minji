package com.ssafy.study.spring_project.global.response;

import com.ssafy.study.spring_project.global.exception.error.ErrorCode;

public record ApiResponse<T>(String message, T data) {
    public static <T> ApiResponse<T> success(String message, T data){
        return new  ApiResponse<>(message, data);
    }

    public static ApiResponse<Void> success(String message){
        return new ApiResponse<>(message, null);
    }

    public static ApiResponse<Void> error(ErrorCode errorcode){
        return new ApiResponse<>(errorcode.getMessage(), null);
    }

    public static <T> ApiResponse<T> error(ErrorCode errorcode, T data){
        return new ApiResponse<>(errorcode.getMessage(), data);
    }

}
