package site.ericblog.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description 通过放射动态创建对象
 * @Author EricGao
 * @Date 14:19 2020/6/23
 */
public class Reflection09 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        //获取class对象
        Class<?> c1 = Class.forName("site.ericblog.reflection.User");

        //构造一个对象
        User user = (User) c1.newInstance(); //本质上调用了类的无参构造器
        System.out.println("user = " + user); //输出结果：user = User{name='null', id=0, age=0}

        //通过构造器创建对象
        System.out.println("----------------------------------我是分割线---------------------------------");
        Constructor<?> declaredConstructor = c1.getDeclaredConstructor(String.class, int.class, int.class);
        User user2 = (User) declaredConstructor.newInstance("ericgao", 007, 28); //通过有参构造器创建对象
        System.out.println("user2 = " + user2);  //输出结果：user2 = User{name='ericgao', id=7, age=28}


        //通过反射调用（操作）普通方法
        System.out.println("----------------------------------我是分割线---------------------------------");
        User user3 = (User)c1.newInstance();
            //通过反射获得一个方法
        Method setName = c1.getDeclaredMethod("setName", String.class);
        setName.invoke(user3, "ericgao"); //invoke表示 启用或激活或执行这个方法
        System.out.println("user3.getName() = " + user3.getName()); //输出结果：user3.getName() = ericgao


        //通过反射调用（操作）类的属性值
        System.out.println("----------------------------------我是分割线---------------------------------");
        User user4 = (User)c1.newInstance();
        Field name = c1.getDeclaredField("name");

        //不能直接操作私有属性，我们需要关闭程序的安全检测，属性或者方法的setAccessible(true)
        name.setAccessible(true);  //设为true时，类的private属性可以访问
        name.set(user4,"ericgao2");
        System.out.println("user4.getName() = " + user4.getName());  //输出结果：user4.getName() = ericgao2
    }
}
