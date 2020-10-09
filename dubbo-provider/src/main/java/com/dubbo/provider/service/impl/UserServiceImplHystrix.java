package com.dubbo.provider.service.impl;

import com.dubbo.provider.mapper.IUserMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.User;
import service.IUserService;

@DubboService(version = "2.0.0")
@Service
public class UserServiceImplHystrix implements IUserService {

    @Autowired
    private IUserMapper userMapper;

    @Override
    @HystrixCommand
    public User getUserById(Integer id) {
        if (Math.random() > 0.5) throw new RuntimeException();
        return userMapper.getUserById(id);
    }

    @Override
    @HystrixCommand
    public int addUser(User user) {
        if (Math.random() > 0.5) throw new RuntimeException();
        return userMapper.addUser(user);
    }

}
