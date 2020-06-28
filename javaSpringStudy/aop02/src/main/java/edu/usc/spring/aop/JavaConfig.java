package edu.usc.spring.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Description TODO
 * @Author EricGao
 * @Date 11:43 2020/6/9
 */
@Configuration
@ComponentScan
@EnableAspectJAutoProxy //开启自动代理
public class JavaConfig {
}
