package site.ericblog.reflection;

import jdk.nashorn.internal.runtime.UserAccessorProperty;

/**
 * @Description 反射
 * @Author EricGao
 * @Date 8:17 2020/6/20
 */
public class Reflection {
    public static void main(String[] args) throws ClassNotFoundException {
        //通过反射获取类的 class 对象
        Class c1 = Class.forName("site.ericblog.reflection.User");
        System.out.println(c1); //输出结果为：class site.ericblog.reflection.User，即得到了User对象

        Class c2 = Class.forName("site.ericblog.reflection.User");
        Class c3 = Class.forName("site.ericblog.reflection.User");
        Class c4 = Class.forName("site.ericblog.reflection.User");
        /*如下，一个类在内存中有且只有一个对应的 Class 对象（hashCode的值相同），一个类被加载后，类的整个结构都会被封装在 Class 对象中*/
        System.out.println(c2.hashCode()); //hashCode的值为 ： 356573597
        System.out.println(c3.hashCode()); //hashCode的值为 ： 356573597
        System.out.println(c4.hashCode()); //hashCode的值为 ： 356573597

    }
}

/**
 * 实体类： pojo, empty
 */
class User{
    private String name;
    private int id;
    private int age;
    public String hello; //只为 测试 使用

    public User(){}

    public User(String name, int id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                '}';
    }
}

