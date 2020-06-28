package edu.usc.spring.ioc.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 加@Configuration注解后表示Java配置类，是一个Java配置类，作用同applicationContext.xml
 *  //@ComponentScan(basePackages = "edu.usc.spring.ioc.service"),//项目启动的时候会扫描 service 包下面的所有实体类，
 *  如果这些类都加了 @Controller, @Service, @Repository,@Component，会把这些类都加到spring容器中去
 * @Author EricGao
 * @Date 8:12 2020/6/7
 */

@Configuration
//若不指定"edu.usc.spring.ioc.service"，则会扫扫描JavaConfig类所在的包下面的所有类和子包中的类
//@ComponentScan(basePackages = "edu.usc.spring.ioc.service")
@ComponentScan(basePackages = "edu.usc.spring.ioc") //为了能够扫描到UserDao

public class JavaConfig {

    /*类对象注入*/
    @Bean //必须，加了此注解后，项目启动时，会自动注册到spring容器中去。@Bean(sh),在此可以定义获取该bean 的方式，
    SayHello sayHello(){
        return new SayHello();
    }
}
