### spring_IOC Java注入配置之对象注入 
三种方式：
- @AutoWired, 根据类型查找，然后赋值，此类型只能有一个对象，不然会报错。
其配合@Qualifer可实现通过变量名查找到变量
- @Resources, 根据名称查找，可用于存在多个实例的注入
- @Injected， 
#### dao(UserDao)
```java
package edu.usc.spring.ioc.dao;

import org.springframework.stereotype.Repository;

/**
 * @Description @Repository(value="userDao")该注解是告诉Spring，让Spring创建一个名字叫“userDao”的UserDaoImpl实例。
 * 当Service需要使用Spring创建的名字叫“userDao”的UserDaoImpl实例时，
 * 就可以使用@Resource(name = "userDao")注解告诉Spring，Spring把创建好的 userDao 注入给 Service 即可。
 * @Author EricGao
 * @Date 18:55 2020/6/7
 */

@Repository
public class UserDao {
    public String hello(){
        return "hello";
    }
}

```

#### service(UserService)

```java
package edu.usc.spring.ioc.service;

import edu.usc.spring.ioc.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author EricGao
 * @Date 8:47 2020/6/7
 */

//配此注解，还需配置好包的扫描(在JavaConfig类中配置)才能加到spring容器中去,
// 默认情况下其Bean的名称是类名首字母小写，如userService
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public List<String> getAllUsers(){
        String hello = userDao.hello();
        System.out.println("hello = " + hello);

        List<String> users = new ArrayList<String>();
        for (int i=0; i<10; i++){
            users.add("EricGao" + i);
        }
        return users;
    }
}

```

#### JavaCofig
```java

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

```


#### 测试
```java
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


```


#### 输出结果
```text
hello, world !!
hello = hello
allUsers = [EricGao0, EricGao1, EricGao2, EricGao3, EricGao4, EricGao5, EricGao6, EricGao7, EricGao8, EricGao9]
```

