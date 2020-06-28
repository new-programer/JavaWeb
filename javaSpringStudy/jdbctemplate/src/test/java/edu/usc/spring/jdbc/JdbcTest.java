package edu.usc.spring.jdbc;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @Description TODO
 * @Author EricGao
 * @Date 20:46 2020/6/9
 */
public class JdbcTest {
    JdbcTemplate jdbcTemplate;

    @Before //单元测试的 Before 注解
    public void before(){
        //需要在单元测试之前获取 JdbcTemplate 实例，方便进行单元测试时使用
        AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(JavaConfig.class);
        jdbcTemplate = acac.getBean(JdbcTemplate.class);
    }

    @Test
    public void test1(){
        /*做一个 插入 操作*/
        jdbcTemplate.update("insert into s1 (id, name, gender, email) values (?,?,?,?);", 1000002, "ericgao", "1", "fightSH@outlook.com");
    }

    @Test
    public void test2(){
        /*做一个 更新 操作*/
        jdbcTemplate.update("update s1 set name=? where id =?;", "fight", 1);
    }

    @Test
    public void test3(){
        /*做一个 删除 操作*/
        jdbcTemplate.update("delete from s1 where id = ?;",  1);

        /*这里等同于，JDBC的写法*/
//        jdbcTemplate.update(new PreparedStatementCreator() {
//            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
//                return null;
//            }
//        });
    }

    @Test
    public void test4(){
        /*做一个 查询 操作*/

        /**
         * 将结果映射到 S1 对象上面来（这个适用于s1表和S1属性不是一一对应的）
         */
//        jdbcTemplate.query("select * from s1", new RowMapper<S1>() {
//            public S1 mapRow(ResultSet resultSet, int i) throws SQLException {
//                return null;
//            }
//        });


        /**
         * 将结果映射到 S1 对象上面来（适用于s1表和S1属性是一一对应的）
         */
        List<S1> s1List = jdbcTemplate.query("select * from s1", new BeanPropertyRowMapper<S1>(S1.class));

        for (int i=0; i< 10; i++){
            System.out.println("s1 = " + s1List.get(i).getName());
        }
    }

    @Test
    public void test5(){

    }
}
