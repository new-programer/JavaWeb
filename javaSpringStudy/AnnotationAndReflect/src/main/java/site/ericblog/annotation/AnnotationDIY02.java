package site.ericblog.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description 测试元注解
 * @Author EricGao
 * @Date 22:32 2020/6/19
 */
public class AnnotationDIY02 {
}

/**
 * 用 @interface 来自定义一个注解
 * Target, 注解使用范围
 *      value = ElementType.METHOD, 表示只在方法上使用该注解才有效
 * Retention, 表示在什么级别该注解有效
 *      1、RetentionPolicy.SOURCE：注解只保留在源文件，当Java文件编译成class文件的时候，注解被遗弃；
 *      2、RetentionPolicy.CLASS：注解被保留到class文件，但jvm加载class文件时候被遗弃，这是默认的生命周期；
 *      3、RetentionPolicy.RUNTIME：注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在；
 *      这3个生命周期分别对应于：Java源文件(.java文件) ---> .class文件 ---> 内存中的字节码。
 */
@Target(value ={ElementType.METHOD, ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@interface MyAnnotation{

}