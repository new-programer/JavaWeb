package site.ericblog.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Description 获取运行时类的完整结构 （field， method，constructor，superclass，interface，annotation）
 * @Author EricGao
 * @Date 6:11 2020/6/23
 */
public class Reflection08 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        Class<?> c1 = Class.forName("site.ericblog.reflection.User");

        //1.获得类的名称
        System.out.println("c1.getName() = " + c1.getName()); //获得 包名+类名 输出结果：c1.getName() = site.ericblog.reflection.User
        System.out.println("c1.getSimpleName() = " + c1.getSimpleName()); //获得类名。输出结果：c1.getSimpleName() = User

        //2.获得类的属性
        System.out.println("----------------------------------我是分割线---------------------------------");

        /**
         * 1.方式一（这种方式只能获得本类或者父类的 public属性）
         * 输出结果：
         *field = public java.lang.String site.ericblog.reflection.User.hello
         */
        System.out.println("--------------------------获得属性->方式一--------------------------");
        Field[] fields = c1.getFields();
        for(Field field : fields){
            System.out.println("field = " + field);  //这种方式，未得到输出结果
        }


        /** 2.方式二（这种方式可获得本类的 所有属性）
         * 输出结果为：
         * field = private java.lang.String site.ericblog.reflection.User.name
         * field = private int site.ericblog.reflection.User.id
         * field = private int site.ericblog.reflection.User.age
         */
        System.out.println("--------------------------获得属性->方式二--------------------------");
        Field[] declaredFields = c1.getDeclaredFields();
        for (Field field : declaredFields){
            System.out.println("field = " + field);
        }



        //3.获得类的方法
        /**
         * 1.方式一（获得本类以及其父类的全部public方法）
         * 输出结果：
         * method = public java.lang.String site.ericblog.reflection.User.toString()
         * method = public void site.ericblog.reflection.User.setName(java.lang.String)
         * method = public void site.ericblog.reflection.User.setAge(int)
         * method = public void site.ericblog.reflection.User.setId(int)
         * method = public final void java.lang.Object.wait() throws java.lang.InterruptedException
         * method = public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
         * method = public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
         * method = public boolean java.lang.Object.equals(java.lang.Object)
         * method = public native int java.lang.Object.hashCode()
         * method = public final native java.lang.Class java.lang.Object.getClass()
         * method = public final native void java.lang.Object.notify()
         * method = public final native void java.lang.Object.notifyAll()
         */
        System.out.println("----------------------------------我是分割线---------------------------------");
        System.out.println("--------------------------获得方法->方式一--------------------------");
        Method[] methods = c1.getMethods();
        for (Method method : methods){
            System.out.println("method = " + method);
        }


        /**
         * 2.方式二（获得本类的所有方法）
         * 输出结果：
         * method = public java.lang.String site.ericblog.reflection.User.toString()
         * method = public void site.ericblog.reflection.User.setName(java.lang.String)
         * method = public void site.ericblog.reflection.User.setAge(int)
         * method = public void site.ericblog.reflection.User.setId(int)
         */
        System.out.println("--------------------------获得方法->方式二--------------------------");
        Method[] declaredMethods = c1.getDeclaredMethods();
        for (Method method : declaredMethods){
            System.out.println("method = " + method);
        }



        //3.获得指定的方法
        /**
         * 输出结果：
         * getName = public java.lang.String site.ericblog.reflection.User.getName()
         * setName = public void site.ericblog.reflection.User.setName(java.lang.String)
         */
        System.out.println("----------------------------------我是分割线---------------------------------");
        Method getName = c1.getMethod("getName",null); //第二个参数 一定不能少
        Method setName = c1.getMethod("setName", String.class);
        System.out.println("getName = " + getName);
        System.out.println("setName = " + setName);



        //4.获得构造器
        System.out.println("----------------------------------我是分割线---------------------------------");

        /**
         * 4.1 方式一（获得public方法）
         * 输出结果：
         * constructor = public site.ericblog.reflection.User()
         * constructor = public site.ericblog.reflection.User(java.lang.String,int,int)
         */
        System.out.println("--------------------------获得构造器->方式一--------------------------");
        Constructor<?>[] constructors = c1.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println("constructor = " + constructor);
        }

        /**
         * 4.2方式二（获得本类的所有方法）
         * 输出结果：
         * constructor = public site.ericblog.reflection.User()
         * constructor = public site.ericblog.reflection.User(java.lang.String,int,int)
         */
        System.out.println("--------------------------获得构造器->方式二--------------------------");
        Constructor<?>[] declaredConstructors = c1.getDeclaredConstructors();
        for (Constructor<?> constructor : declaredConstructors) {
            System.out.println("constructor = " + constructor);
        }

        //5.获得指定的构造器
        /**
         * 输出结果：
         * declaredConstructor = public site.ericblog.reflection.User(java.lang.String,int,int)
         */
        System.out.println("----------------------------------我是分割线---------------------------------");
        Constructor<?> declaredConstructor = c1.getDeclaredConstructor(String.class, int.class, int.class);
        System.out.println("declaredConstructor = " + declaredConstructor);

    }
}
