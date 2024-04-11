package com.afa.adminback.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.afa.adminback.service.IUserService;
import com.afa.adminback.entity.User;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 阿发
 * @since 2024-04-10
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    @PostMapping
    public boolean save(@RequestBody User user) {
            return userService.saveOrUpdate(user);
    }
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
            return userService.removeById(id);
    }
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
            return userService.removeByIds(ids);
    }
    @GetMapping
    public List<User> findAll() {
            return userService.list();
    }

    @GetMapping("/{id}")
    public User findOne(@PathVariable Integer id) {
            return userService.getById(id);
    }

    @GetMapping("/page")
    public Page<User> findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam(defaultValue = "") String username,
                               @RequestParam(defaultValue = "") String email,
                               @RequestParam(defaultValue = "") String address) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if(!"".equals(username)) {
            queryWrapper.like("username", username);
        }
        if(!"".equals(email)) {
            queryWrapper.like("email", email);
        }
        if(!"".equals(address)) {
            queryWrapper.like("address", address);
        }
        return userService.page(new Page<>(pageNum, pageSize), queryWrapper);

    }

}
