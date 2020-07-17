package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.dto.UserSearchDTO;
import com.example.pojo.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDAO extends BaseMapper<User> {

    @Select("select * from user")
    List<User> list(UserSearchDTO searchDTO);

}
