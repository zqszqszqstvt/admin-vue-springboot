package com.afa.adminback.controller;

import com.afa.adminback.entity.User;
import com.afa.adminback.mapper.UserMapper;
import com.afa.adminback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<User> findAll() {
        List<User> all = userMapper.findAll();
        return all;
    }

    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable Integer id) {
        return userMapper.deleteById(id);
    }

    // 分页查询接口  @RequestParam接收?pageNum=1&Size=10
    @GetMapping("/page")
    public Map<String, Object> findPage(@RequestParam Integer pageNum, Integer pageSize) {
        pageNum = (pageNum - 1) * pageSize;
        List<User> data = userMapper.selectPage(pageNum, pageSize);
        Integer total = userMapper.selectTotal();
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }
}
