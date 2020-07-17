package com.example.util;

public final class ValidationPattern {
    public static final String USERNAME = "^[A-Za-z0-9]{4,18}$";
    public static final String PASSWORD = "^[A-Za-z0-9~!@#$%^&()_+\\-*/=]{4,18}$";
    public static final String NICKNAME = "^\\S{4,12}$";

    private ValidationPattern() {
    }
}
