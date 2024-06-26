package com.example.newstw.enums;

import lombok.Getter;

@Getter
public enum ErrorCode {

    USERNAME_NOT_FOUND(401),
    ENTITY_NOT_FOUND(402),
    ACCESS_DENIED(403),
    PASSWORD_MISMATCH(404),
    USER_NOT_FOUND(405),
    USER_EXISTS(406);


    private final int statusCode;

    ErrorCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
