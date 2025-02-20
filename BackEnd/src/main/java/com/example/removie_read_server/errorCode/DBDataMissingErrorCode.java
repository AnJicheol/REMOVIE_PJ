package com.example.removie_read_server.errorCode;

import lombok.Getter;

@Getter
public enum DBDataMissingErrorCode {
    DB_DATA_MISSING("서버측 오류로 버전 동기화 할 수 없음", "DB_DATA_MISSING");

   private final String message;
   private final String code;

    DBDataMissingErrorCode(String message, String code) {
        this.message = message;
        this.code = code;
    }
}
