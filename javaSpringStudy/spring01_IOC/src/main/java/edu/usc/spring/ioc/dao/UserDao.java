package edu.usc.spring.ioc.dao;

import org.springframework.stereotype.Repository;

/**
 * @Description @Repository(value="userDao")该注解是告诉Spring，让Spring创建一个名字叫“userDao”的UserDaoImpl实例。
 * 当Service需要使用Spring创建的名字叫“userDao”的UserDaoImpl实例时，
 * 就可以使用@Resource(name = "userDao")注解告诉Spring，Spring把创建好的 userDao 注入给 Service 即可。
 * @Author EricGao
 * @Date 18:55 2020/6/7
 */

@Repository
public class UserDao {
    public String hello(){
        return "hello";
    }
}
