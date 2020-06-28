### spring_IOC之复杂属性注入
主要包括类注入，数组注入，Map 注入和 Properties 注入
#### 增加了复杂属性的User
> 注：复杂数字包括类属性，数组属性，Map属性和Properties属性

```java
package edu.usc.spring.ioc.model;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Description TODO
 * @Author EricGao
 * @Date 12:13 2020/6/6
 */
public class User {
    private String username;
    private String address;
    private Integer id;

    private Cat cat;
    private Cat[] cats;
    private List<String> favorites;
    private Map<String, Object> details; //这里写成Object 方便在注入的时候接受任何类型的值
    private Properties info;

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

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public Cat[] getCats() {
        return cats;
    }

    public void setCats(Cat[] cats) {
        this.cats = cats;
    }

    public List<String> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<String> favorites) {
        this.favorites = favorites;
    }

    public Map<String, Object> getDetails() {
        return details;
    }

    public void setDetails(Map<String, Object> details) {
        this.details = details;
    }

    public Properties getInfo() {
        return info;
    }

    public void setInfo(Properties info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", id=" + id +
                ", cat=" + cat +
                ", cats=" + Arrays.toString(cats) +
                ", favorites=" + favorites +
                ", details=" + details +
                ", info=" + info +
                '}';
    }
}

```
#### User中用到的Cat
```java
package edu.usc.spring.ioc.model;

/**
 * @Description TODO
 * @Author EricGao
 * @Date 20:04 2020/6/6
 */
public class Cat {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

```

#### xml配置
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:p="http://www.springframework.org/schema/p"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--                ！！！复杂属性的注入！！！                 -->
    <!--1.类对象的注入-->
    <bean class="edu.usc.spring.ioc.model.User" id="user5">
        <property name="id" value="4" />
        <property name="username" value="hejun" />
        <property name="address" value="sh, china" />

        <!--1.类对象 的注入-->
        <property name="cat">
            <bean class="edu.usc.spring.ioc.model.Cat">
                <property name="name" value="cat_miao" />
                <property name="age" value="5" />
            </bean>
        </property>

        <!--2.类数组 的注入-->
        <property name="cats">
            <array>
                <bean class="edu.usc.spring.ioc.model.Cat" id="cat2">
                    <property name="age" value="3" />
                    <property name="name" value="mimi" />
                </bean>
            </array>
        </property>

        <!--3. List 注入-->
        <property name="favorites">
            <list>
                <value>basketball</value>
                <value>football</value>
            </list>
        </property>

        <!--4.Map的注入-->
        <property name="details" >
            <map>
                <entry key="gender" value="male" />
                <entry key="age" value="22" />
            </map>
        </property>

        <!--5.Properties 的注入-->
        <property name="info" >
            <props>
                <prop key="phone">10086</prop>
            </props>
        </property>

    </bean>

</beans>
```

#### 获得User实例并输出
```java
package edu.usc.spring.ioc;

import edu.usc.spring.ioc.model.User;
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
        ClassPathXmlApplicationContext cpxac = new ClassPathXmlApplicationContext("testContext.xml");  //为主，常用
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
        User user = (User) cpxac.getBean("user");  //从spring容器获取User实例方式一
        //2.输出
        System.out.println("user = " + user);

    }
}


```

#### 输出结果
```text
username = hejun
user = User{username='hejun', address='sh, china', id=4, cat=Cat{name='cat_miao', age=5}, cats=[Cat{name='mimi', age=3}], favorites=[basketball, football], details={gender=male, age=22}, info={phone=10086}}
```

