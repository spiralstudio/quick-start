package com.example.enums;

public enum UserStatusEnum {
    ENABLED(1),
    DISABLED(2);

    private final int code;

    UserStatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
