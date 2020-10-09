package com.dubbo.provider.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import pojo.User;

/**
 * 用户映射接口
 */
@Mapper
public interface IUserMapper {

    @Select("select * from user where id = #{id}")
    User getUserById(Integer id);

    @Insert("insert into user (username, age) values (#{username}, #{age})")
    int addUser(User user);

}
