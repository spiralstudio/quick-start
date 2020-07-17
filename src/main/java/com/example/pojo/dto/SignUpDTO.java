package com.example.pojo.dto;

import com.example.constant.Message;
import com.example.util.ValidationPattern;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class SignUpDTO {
    @NotNull(message = Message.USERNAME_REQUIRED)
    @Pattern(regexp = ValidationPattern.USERNAME, message = Message.USERNAME_INVALID)
    private String username;

    @NotNull(message = Message.PASSWORD_REQUIRED)
    @Pattern(regexp = ValidationPattern.PASSWORD, message = Message.PASSWORD_INVALID)
    private String password;

    @NotNull(message = Message.NICKNAME_REQUIRED)
    @Pattern(regexp = ValidationPattern.NICKNAME, message = Message.NICKNAME_INVALID)
    private String nickname;

}
