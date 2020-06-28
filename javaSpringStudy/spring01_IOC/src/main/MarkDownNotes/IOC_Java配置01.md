### spring_IOC Java注入配置之
#### Bean(SayHello)
```java
package edu.usc.spring.ioc.javaconfig;

/**
 * @Description TODO
 * @Author EricGao
 * @Date 8:11 2020/6/7
 */
public class SayHello {
    public String sayHello(String string){
        return string;
    }
}

```

#### JavaConfig

```java
package edu.usc.spring.ioc.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 加@Configuration注解后表示Java配置类，是一个Java配置类，作用同applicationContext.xml
 * @Author EricGao
 * @Date 8:12 2020/6/7
 */

@Configuration
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

import edu.usc.spring.ioc.javaconfig.JavaConfig;
import edu.usc.spring.ioc.javaconfig.SayHello;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
    }
}

```


#### 输出结果
```text
hello, world !!
```

