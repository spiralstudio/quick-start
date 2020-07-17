package com.example.service;

import com.example.pojo.dto.UserSearchDTO;
import com.example.pojo.entity.User;
import com.example.pojo.vo.UserVO;
import com.example.util.Result;
import io.leego.mypages.util.Page;

public interface UserService {

    Result<Page<User>> listUser(UserSearchDTO searchDTO);

    Result<UserVO> getUser(Long id);

}
