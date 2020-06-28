package edu.usc.springboot.diy.JavaConfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @Description : 自定义配置包的 spring 注解
 * @Author EricGao
 * @Date 17:40 2020/6/10
 */

/**
 * @Configuration 注解表示这是一个配置类，在我们这里，这个配置的作用类似于 applicationContext.xml
 * @ComponentScan 注解表示配置包扫描，里边的属性和 xml 配置中的属性都是一一对应的，
 * useDefaultFilters 表示使用默认的过滤器，然后又除去 Controller 注解，
 * 即在 Spring 容器中扫描除了 Controller 之外的其他所有 Bean
 */
@Configuration
@ComponentScan(basePackages = "edu.usc.springboot.diy", useDefaultFilters = true,
    excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class)})
public class SpringConfig {

}
