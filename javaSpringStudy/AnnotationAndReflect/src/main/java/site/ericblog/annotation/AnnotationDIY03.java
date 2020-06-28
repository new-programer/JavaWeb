package site.ericblog.annotation;

import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description TODO
 * @Author EricGao
 * @Date 22:42 2020/6/19
 */
public class AnnotationDIY03 {
    @MyAnnotation02(age = 27, name = "ericgao")
    public void test(){}

    @MyAnnotation03("ericgao") //只有当注解 MyAnnotation03只有一个参数是，才可以省略参数名称
    public void test2(){}
}

@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation02{
    //注解参数： 参数类型 + 参数名()
    //default 后面为默认值
    String name() default "";

    int age() default 0;

    int id() default - 1;

    String[] school() default {"GitHub", "GitLab"};
}

@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation03{
    String value();
}