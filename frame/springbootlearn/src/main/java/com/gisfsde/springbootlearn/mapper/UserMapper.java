package com.gisfsde.springbootlearn.mapper;


import com.gisfsde.springbootlearn.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * sql映射文件的namespace必须和mapper接口的全限定类名保持一致
 * mapper接口的接口方法名必须和xml中的sql语句id保持一致
 * mapper接口的接口方法形参类型必须和sql语句的输入参数类型保持一致
 * mapper接口的接口方法返回类型必须和sql语句的resultType保持一致
 * 其中前两个条件是必须需要满足的，否则会报错，后面两个条件最好满足，如果真的没有写在一些条件下是不会报错的。*/
@Mapper
public interface UserMapper {

    public List<User> findUserByName(String userName);

    public List<User> ListUser();

    public List<User> queryPage(Integer startRows);

    public int getRowCount();

    public int insertUser(User user);

    public int delete(int userId);

    public int Update(User user);
}

