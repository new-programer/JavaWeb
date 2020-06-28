package edu.usc.spring.ioc.service;

import edu.usc.spring.ioc.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author EricGao
 * @Date 8:47 2020/6/7
 */

//配此注解，还需配置好包的扫描(在JavaConfig类中配置)才能加到spring容器中去,
// 默认情况下其Bean的名称是类名首字母小写，如userService
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public List<String> getAllUsers(){
        String hello = userDao.hello();
        System.out.println("hello = " + hello);

        List<String> users = new ArrayList<String>();
        for (int i=0; i<10; i++){
            users.add("EricGao" + i);
        }
        return users;
    }
}
