package com.example.controller;

import com.example.pojo.dto.UserSearchDTO;
import com.example.pojo.entity.User;
import com.example.pojo.vo.UserVO;
import com.example.service.UserService;
import com.example.util.Result;
import io.leego.mypages.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public Result<Page<User>> listUser(UserSearchDTO searchDTO) {
        return userService.listUser(searchDTO);
    }

    @GetMapping("{id}")
    public Result<UserVO> getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

}
