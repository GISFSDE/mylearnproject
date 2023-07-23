package com.gisfsde.springbootlearn.service;



import com.gisfsde.springbootlearn.entity.User;
import com.gisfsde.springbootlearn.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**为什么要写service接口？
 * 情景1:在开源框架中有很多这种情况,就是某个功能支持用户自定义扩展.说白了,它提供了一个接口,我们只需要实现这个接口,把我们自己的实现逻辑补上,就可以让框架按照我们的逻辑来执行.问题来了,框架的作者并不知道我们的实现类是什么,如果不定义一个接口,那么要如何在框架中调用我们的实现类呢?
 * 情景2:我和同事分别做项目的2个不同功能模块,但是同事的功能中却需要调用我这头实现的部分逻辑.为了让他有一个"占位符"可用,我是不是应该快速的写个接口扔给他呢?
 * 情景3:一个适配器功能,或是说一个简单的工厂类,如果没有定义接口,那么面对众多实现类,要如何统一操作呢?
 * 情景4:想让项目的代码符合某种"规范",但是又不可能看着别人写代码吧,那好办,先出一套接口,然后你们就看着办~
 * 情景5:java中没有多继承,但是可以多实现接口,那么就有一件很有趣的事情了,一个实现类可以实现多个接口,然后此时接口可以有选择的暴露实现类的部分方法,做到"窄化"实现类功能的目的.*/
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> findByName(String userName) {
        return userMapper.findUserByName(userName);
    }

    public List<User> queryPage(Integer startRows) {
        return userMapper.queryPage(startRows);
    }

    public int getRowCount() {
        return userMapper.getRowCount();
    }

    public User insertUser(User user) {
        userMapper.insertUser(user);
        return user;
    }

    public List<User> ListUser(){
        return userMapper.ListUser();
    }

    public int Update(User user){
        return userMapper.Update(user);
    }

    public int delete(int userId){
        return userMapper.delete(userId);
    }

}

