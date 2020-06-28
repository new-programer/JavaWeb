package edu.usc.spring.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description TODO
 * @Author EricGao
 * @Date 21:37 2020/6/9
 */

@Service
public class AccountService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Transactional  //事务注解，也可将其加到 AccountService 类上面，
    public void transfer(){
        jdbcTemplate.update("update account set money=money-500 where username=?", "zhangsan");
        int i = 1 / 0;
        jdbcTemplate.update("update account set money=money+500 where username=?", "lisi");
    }
}
