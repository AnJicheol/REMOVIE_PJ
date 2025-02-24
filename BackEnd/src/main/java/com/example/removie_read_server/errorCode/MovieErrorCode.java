package com.example.removie_read_server.errorCode;

import lombok.Getter;

@Getter
public enum MovieErrorCode {

    MOVIE_CODE_INVALID("일치 하는 영화 코드가 존재 하지않습니다.", "MOVIE_CODE_INVALID"),
    MOVIE_TITLE_INVALID("일치 하는 영화 제목이 존재 하지않습니다.", "MOVIE_TITLE_INVALID"),
    MOVIE_CODE_SIZE_LARGE("요청 크기가 허용하는 크기를 초과했습니다.", "MOVIE_CODE_SIZE_LARGE");

    private final String message;
    private final String code;

    MovieErrorCode(String message, String code) {
        this.message = message;
        this.code = code;
    }
}
