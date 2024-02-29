package com.afa.adminback.service;

import com.afa.adminback.entity.User;
import com.afa.adminback.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    public boolean saveUser(User user) {
//        if(user.getId() == null) {
//            return save(user);  // mybatis-plus提供的方法，插入数据
//        } else {
//            return updateById(user);
//        }
        return saveOrUpdate(user);
    }
//    @Autowired
//    private UserMapper userMapper;
//    public int save(User user) {
//        if(user.getId() == null) {  // 没有查到存在的id，则新增用户
//            return userMapper.insert(user);
//        } else {
//            return userMapper.update(user);
//        }
//    }
}
