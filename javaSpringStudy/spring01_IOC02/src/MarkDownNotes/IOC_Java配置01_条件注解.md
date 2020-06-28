### spring_IOC Java注入配置之条件注解

#### 接口
```java
package edu.usc.spring.ioc;

/**
 * @Description TODO
 * @Author EricGao
 * @Date 19:30 2020/6/7
 */
public interface ShowCmd {
    String showCmd();
}

```

#### 实现类
- WindowsShowCmd
```java
package edu.usc.spring.ioc;

/**
 * @Description TODO
 * @Author EricGao
 * @Date 19:30 2020/6/7
 */
public class WindowsShowCmd implements ShowCmd{

    public String showCmd(){
        return "dir";
    }
}

```

- LinuxShowCmd
```java
package edu.usc.spring.ioc;

/**
 * @Description TODO
 * @Author EricGao
 * @Date 19:30 2020/6/7
 */
public class LinuxShowCmd implements ShowCmd{
    public String showCmd(){
        return "ls";
    }
}

```
#### 条件设置
- WindowsCondition
```java
package edu.usc.spring.ioc;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @Description TODO
 * @Author EricGao
 * @Date 19:30 2020/6/7
 */
public class WindowsCondition implements Condition {
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String osName = conditionContext.getEnvironment().getProperty("os.name");
        return osName.toLowerCase().contains("win");
    }
}

```

- LinuxCondition

```java
package edu.usc.spring.ioc;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @Description 当 matches 返回true的时候，相应的bean才会生效
 * @Author EricGao
 * @Date 19:30 2020/6/7
 */
public class LinuxCondition implements Condition {

    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String osName = conditionContext.getEnvironment().getProperty("os.name");
        return osName.toLowerCase().contains("linux");
    }

}

```

#### JavaCofig(Java配置类)
```java
package edu.usc.spring.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @Description TODO
 * @Author EricGao
 * @Date 19:35 2020/6/7
 */

@Configuration
public class JavaConfig {
    @Bean("cmd")
    @Conditional(WindowsCondition.class)
    ShowCmd winCmd(){
        return new WindowsShowCmd();
    }

    @Bean("cmd")
    @Conditional(LinuxCondition.class)
    ShowCmd linuxCmd() {
        return new LinuxShowCmd();
    }
}

```


#### 测试
```java
package edu.usc.spring.ioc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description TODO
 * @Author EricGao
 * @Date 19:41 2020/6/7
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(JavaConfig.class);
        ShowCmd cmd = (ShowCmd) acac.getBean("cmd");
        String cmdStr = cmd.showCmd();
        System.out.println("cmdStr = " + cmdStr);
    }
}

```


#### 输出结果
```text
cmdStr = dir
```

