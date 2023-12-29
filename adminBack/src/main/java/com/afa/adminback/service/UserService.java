package com.afa.adminback.service;

import com.afa.adminback.entity.User;
import com.afa.adminback.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public int save(User user) {
        if(user.getId() == null) {  // 没有查到存在的id，则新增用户
            return userMapper.insert(user);
        } else {
            return userMapper.update(user);
        }
    }
}
