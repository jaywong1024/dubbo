package com.dubbo.provider.service.impl;

import com.dubbo.provider.mapper.IUserMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.User;
import service.IUserService;

/**
 * 使用 @DubboService 声明需要暴露的服务
 */
@DubboService(version = "0.0.1")
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        try {
            System.out.println("请求 getUserById ...");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userMapper.getUserById(id);
    }

    @Override
    public int addUser(User user) {
        System.out.println("请求 addUser ...");
        int result = userMapper.addUser(user);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }


}
