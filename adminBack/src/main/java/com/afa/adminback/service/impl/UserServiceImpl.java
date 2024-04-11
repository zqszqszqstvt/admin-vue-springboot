package com.afa.adminback.service.impl;

import com.afa.adminback.entity.User;
import com.afa.adminback.mapper.UserMapper;
import com.afa.adminback.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 阿发
 * @since 2024-03-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
