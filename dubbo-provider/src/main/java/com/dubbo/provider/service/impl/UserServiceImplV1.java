package com.dubbo.provider.service.impl;

import com.dubbo.provider.mapper.IUserMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.User;
import service.IUserService;

@DubboService(version = "1.0.0")
@Service
public class UserServiceImplV1 implements IUserService {

    @Autowired
    private IUserMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }
}
