package edu.usc.spring.jdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * @Description TODO
 * @Author EricGao
 * @Date 20:40 2020/6/9
 */
@Configuration
@ComponentScan  //将 @Service注解的类扫进去
public class JavaConfig {
    @Bean
    DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3307/test?serverTimezone=Asia/Shanghai");
        dataSource.setUsername("root");
        dataSource.setPassword("ericgao");
        return dataSource;
    }

    @Bean
    JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }
}
