package com.example.constant;

public final class Message {
    /** common */
    public static final String PERMISSION_DENIED = "Permission denied";
    public static final String OK = "OK";
    public static final String SUCCESS = "Success";
    public static final String FAILURE = "Failure";
    public static final String SAVE_SUCCESS = "Save successfully";
    public static final String UPDATE_SUCCESS = "Update successfully";
    public static final String DELETE_SUCCESS = "Delete successfully";
    public static final String SAVE_FAILURE = "Failed to save";
    public static final String UPDATE_FAILURE = "Failed to update";
    public static final String DELETE_FAILURE = "Failed to delete";
    /** username */
    public static final String USERNAME_REQUIRED = "Please enter the username";
    public static final String USERNAME_INVALID = "Incorrect username format";
    public static final String USERNAME_EXISTS = "Username already exists";
    /** password */
    public static final String PASSWORD_REQUIRED = "Please enter the password";
    public static final String PASSWORD_INVALID = "Incorrect password format";
    /** nickname */
    public static final String NICKNAME_REQUIRED = "Please enter the nickname";
    public static final String NICKNAME_INVALID = "Incorrect nickname format";
    /** account */
    public static final String ACCOUNT_INCORRECT = "Incorrect username and password";
    public static final String ACCOUNT_DOES_NOT_EXIST = "Account does not exist";
    public static final String ACCOUNT_UNAUTHORIZED = "Please sign in";

    private Message() {
    }

}
