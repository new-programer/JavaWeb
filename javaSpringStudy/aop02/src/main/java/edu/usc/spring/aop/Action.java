package edu.usc.spring.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description 自定义一个 Action 注解, 属于侵入式的注解（需要改源码）
 * @Author EricGao
 * @Date 10:46 2020/6/9
 */
@Target(ElementType.METHOD) //表示只用到 方法 上
@Retention(RetentionPolicy.RUNTIME) //即运行的时候还要存在
public @interface Action {
}
