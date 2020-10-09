package com.dubbo.consumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Method;
import org.springframework.web.bind.annotation.*;
import pojo.User;
import service.IUserService;

import javax.validation.Valid;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class UserController {

    @DubboReference(
//            设置集群容错模式
            cluster = "failover",
//            设置负载均衡的策略
            loadbalance = "roundrobin",
//            设置直连的服务地址
//            url = "dubbo://localhost:20880",
//            设置使用版本号为 1.0.0 的服务
            version = "2.0.0",
            methods = {
//                    设置非幂等方法 addUser 的重试次数为 0
                    @Method(name = "addUser", retries = 0)
            }
    )
    private IUserService userService;

    /**
     * 通过 id 获取用户信息
     * 调用出现异常则回调 error 方法
     */
    @GetMapping("/user/{id}")
    @HystrixCommand(fallbackMethod = "error")
    public User user(@PathVariable("id") Integer id) {
        return Optional.ofNullable(id).isEmpty() ? null : userService.getUserById(id);
    }

    /**
     * 发生异常调用此方法
     */
    public User error(@PathVariable("id") Integer id) {
        return new User(id, "error", 0);
    }

    /**
     * 输入 username 和 age 添加用户
     */
    @PostMapping("/user")
    public int user(@RequestBody @Valid User user) {
        return userService.addUser(user);
    }


}
