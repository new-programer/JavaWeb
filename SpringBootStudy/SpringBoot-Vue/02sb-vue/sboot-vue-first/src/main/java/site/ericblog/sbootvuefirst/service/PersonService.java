package site.ericblog.sbootvuefirst.service;

import site.ericblog.sbootvuefirst.pojo.Person;

import java.util.List;

/**
 * @Description 个人信息Service
 * @Author EricGao
 * @Date 13:30 2020/6/27
 */
public interface PersonService {
    /**
     * 查询所有的用户
     */
    List<Person> findAll();
}
