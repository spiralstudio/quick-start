package com.example.service.impl;

import com.example.dao.UserDAO;
import com.example.pojo.dto.UserSearchDTO;
import com.example.pojo.entity.User;
import com.example.pojo.vo.UserVO;
import com.example.service.UserService;
import com.example.util.Result;
import io.leego.mypages.util.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public Result<Page<User>> listUser(UserSearchDTO searchDTO) {
        return Result.buildSuccess(Page.of(userDAO.list(searchDTO)));
    }

    @Override
    public Result<UserVO> getUser(Long id) {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userDAO.selectById(id), userVO);
        return Result.buildSuccess(userVO);
    }

}
