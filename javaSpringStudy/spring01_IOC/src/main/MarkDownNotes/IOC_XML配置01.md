### spring_IOC之基本配置及使用

#### bean定义(User)

```java
package edu.usc.spring.ioc.model;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

/**
 * @Description TODO
 * @Author EricGao
 * @Date 12:13 2020/6/6
 */
public class User {
    private String username;
    private String address;
    private Integer id;

    public User(){
    }


    /*重写构造方法，方便注入*/
    public User(String username, String address, Integer id) {
        this.username = username;
        this.address = address;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        System.out.println("username = " + username);
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", id=" + id +
                '}';
    }
}

```

#### xml配置 (applicationContext.xml)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:p="http://www.springframework.org/schema/p"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--注入方式一： 通过构造函数-->
    <bean class="edu.usc.spring.ioc.model.User" id="user">
        <!--注意：User的有参构造方法有几个参数，这里就应该配置几个参数，不然会报错-->
        <constructor-arg name="id" value="1"/>
        <constructor-arg name="username" value="ericgao" />
        <constructor-arg name="address" value="USC, HengYang" />
    </bean>

    <!--注入方式二： 通过set方法(这种方式最常用)-->
    <bean class="edu.usc.spring.ioc.model.User" id="user3">
        <!--set方法注入配置-->
        <property name="id" value="2" />
        <property name="username" value="peterli" />
        <property name="address" value="HNU, ChangSha" />
    </bean>

    <!--注入方式三： 通过通过 p 名称空间（本质上也是set方法注入）
    需要导入名称空间：xmlns:p="http://www.springframework.org/schema/p"
    -->
    <bean class="edu.usc.spring.ioc.model.User" id="user4" p:username="spring" p:id="3" p:address="TsingHua"/>
</beans>

```

#### 在主类(Main)中获取并输出容器中的实例

```java

package edu.usc.spring.ioc.model;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @Description TODO
 * @Author EricGao
 * @Date 12:13 2020/6/6
 */
public class Main {
    public static void main(String[] args) {
//        User user = new User();
//        System.out.println("user: "+user);

        /*加载spring的xml配置文件,获取创建ClassPathXmlApplicationContext对象的
        过程（new ClassPathXmlApplicationContext("applicationContext.xml")），会对User类进行初始化
        初始化之后，User的实例被放在spring容器里面，若需要这个实例，直接从spring容器里面获取即可*/
        ClassPathXmlApplicationContext cpxac = new ClassPathXmlApplicationContext("applicationContext.xml");  //为主，常用
//        FileSystemXmlApplicationContext fsxac = new FileSystemXmlApplicationContext("applicationContext.xml文件的绝对路径"); //一般不用

        GetAndOutputInstance(cpxac);

        /*输出结果如下：
            * 已被User被初始化-------------------说明创建对象的时候User实例被初始化
            * user1 = edu.usc.spring.ioc.model.User@464bee09
            * user2 = edu.usc.spring.ioc.model.User@464bee09
            * user3 = edu.usc.spring.ioc.model.User@464bee09
            * 以上三个User的实例的是一样的，说明，以上三种方式获取的User实例都是同一个
            * */

    }

    /**
     *获取并输出User实例的内容
     * @param cpxac 用于传递 spring 容器对象引用
     */
    private static void GetAndOutputInstance(ClassPathXmlApplicationContext cpxac) {
        /*从spring容器中获取User实例的方式*/
        //1.通过名称方式获取
        User user1 = (User) cpxac.getBean("user");  //从spring容器获取User实例方式一
        //2.通过类型方式获取
//        User user2 = cpxac.getBean(User.class); //从spring容器获取User实例方式二
/*    如果xml配置文件中出现两相同的class类型（如下），会报错“class不唯一错误”
        <bean class="edu.usc.spring.ioc.model.User" id="user">
            <!--注意：User的有参构造方法有几个参数，这里就应该配置几个参数，不然会报错-->
            <constructor-arg name="id" value="1"/>
            <constructor-arg name="username" value="ericgao" />
            <constructor-arg name="address" value="USC, HengYang" />
        </bean>

        <bean class="edu.usc.spring.ioc.model.User" id="user2">
            <!--set方法注入配置-->
            <property name="id" value="2" />
            <property name="username" value="peterli" />
            <property name="address" value="HNU, ChangSha" />
        </bean>
                */
        //3.通过名称和类型组合方式获取
        User user3 = cpxac.getBean("user3",User.class); //从spring容器获取User实例方式三


        User user4 = cpxac.getBean("user4", User.class);
        /*输出三种方式获取的到的User实例*/
        System.out.println("user1 = " + user1);
//        System.out.println("user2 = " + user2);
        System.out.println("user3 = " + user3);
        System.out.println("user4 = " + user4);
    }

}
```

#### main方法运行后的输出

```text
username = peterli
username = spring
user1 = User{username='ericgao', address='USC, HengYang', id=1}
user3 = User{username='peterli', address='HNU, ChangSha', id=2}
user4 = User{username='spring', address='TsingHua', id=3}
```