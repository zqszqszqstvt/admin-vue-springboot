package com.afa.adminback.controller;

import com.afa.adminback.entity.User;
import com.afa.adminback.mapper.UserMapper;
import com.afa.adminback.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    // 新增和修改
    @PostMapping
    public boolean save(@RequestBody User user) {
        return userService.saveUser(user);
    }
    //查询所有用户
    @GetMapping
    public List<User> findAll() {
        return userService.list();
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return userService.removeById(id);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return userService.removeByIds(ids);
    }

    // 分页查询接口  @RequestParam接收?pageNum=1&Size=10
//    @GetMapping("/page")
//    public Map<String, Object> findPage(@RequestParam Integer pageNum,
//                                        @RequestParam Integer pageSize,
//                                        @RequestParam String username
//    ) {
//        pageNum = (pageNum - 1) * pageSize;
//        List<User> data = userMapper.selectPage(pageNum, pageSize, username);
//        Integer total = userMapper.selectTotal(username);
//        Map<String, Object> res = new HashMap<>();
//        res.put("data", data);
//        res.put("total", total);
//        return res;
//    }

    //分页查询-mybatis-plus方式
    @GetMapping("/page")
    public IPage<User> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String username,
                                @RequestParam(defaultValue = "") String address,
                                @RequestParam(defaultValue = "") String email
    ) {
        IPage<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like("username", username);
//         若有多个条件应该判断是否为空，再字符串模糊搜索 如下
        if(!"".equals(username)) {
            queryWrapper.like("username", username);
        }
        if(!"".equals(address)) {
            queryWrapper.like("address", address);
        }
        if(!"".equals(email)) {
            queryWrapper.like("email", email);
        }
        queryWrapper.orderByDesc("id");
        return userService.page(page, queryWrapper);
    }
}
