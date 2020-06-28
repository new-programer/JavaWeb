package edu.usc.spring.ioc;

import com.sun.xml.internal.stream.buffer.stax.StreamReaderBufferCreator;
import edu.usc.spring.ioc.javaconfig.JavaConfig;
import edu.usc.spring.ioc.javaconfig.SayHello;
import edu.usc.spring.ioc.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * @Description TODO
 * @Author EricGao
 * @Date 8:17 2020/6/7
 */
public class JavaConfigTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(JavaConfig.class);
        /*根据名称和类型获取实例*/
        SayHello sayHello = acac.getBean("sayHello", SayHello.class);//这里的 sayHello 是 JavaConfig 类中的 sayHello方法名
//        SayHello sh = acac.getBean("sh", SayHello.class); //前提是 SayHello类的Bean注解为：@Bean(sh)
        System.out.println(sayHello.sayHello("hello, world !!"));

        /*获取UserService的实例 userService*/
        UserService userService = acac.getBean(UserService.class);
        List<String> allUsers = userService.getAllUsers();
        System.out.println("allUsers = " + allUsers);
    }
}
