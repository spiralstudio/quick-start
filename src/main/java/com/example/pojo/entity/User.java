package com.example.pojo.entity;

import lombok.Data;

@Data
public class User extends BaseEntity {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private Integer status;
    private String salt;
}
