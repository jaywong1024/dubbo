package service;

import pojo.User;

/**
 * 用户业务接口
 */
public interface IUserService {

    /**
     * 根据 id 获取用户信息
     */
    User getUserById(Integer id);

    /**
     * 添加用户信息
     */
    int addUser(User user);

}
