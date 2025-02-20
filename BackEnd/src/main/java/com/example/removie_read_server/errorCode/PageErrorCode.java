package com.example.removie_read_server.errorCode;

import lombok.Getter;

@Getter
public enum PageErrorCode {
    PAGE_RANGE_INVALID("페이지 범위가 유효하지 않습니다.", "PAGE_RANGE_INVALID");

    private final String message;
    private final String code;


    PageErrorCode(String message, String code) {
        this.message = message;
        this.code = code;
    }
}
