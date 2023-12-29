package com.afa.adminback.controller;

import com.afa.adminback.entity.User;
import com.afa.adminback.mapper.UserMapper;
import com.afa.adminback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    // 新增和修改
    @PostMapping
    public Integer save(@RequestBody User user) {
        return userService.save(user);
    }
    //查询所有用户
    @GetMapping
    public List<User> index() {
        List<User> all = userMapper.findAll();
        return all;
    }

    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable Integer id) {
        return userMapper.deleteById(id);
    }
}
