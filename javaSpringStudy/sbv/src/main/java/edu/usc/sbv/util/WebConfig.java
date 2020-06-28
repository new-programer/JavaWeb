package edu.usc.sbv.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Description 全局配置类--------此处用于配置跨域请求
 * @Author EricGao
 * @Date 17:12 2020/6/14
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        /**
         * 1.欲访问的路径
         * 2.请求来源
         * 3.方法
         * 4.是否允许携带信息
         * 5.最大时间
         */
        registry.addMapping("/**") //跨域对象，即访问什么是需要跨域
                .allowedOrigins("Http://localhost:8080", "null") //前后端分离的前端访问地址
                .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE") //规定允许跨域的方法，一共可设置5种
                .allowCredentials(true) //表示是否携带信息
                .maxAge(3600);
    }
}
