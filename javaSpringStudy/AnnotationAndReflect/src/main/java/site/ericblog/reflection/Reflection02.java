package site.ericblog.reflection;

/**
 * @Description Object 类中定义了 public final Class getClass()方法，该方法将被所有子类继承。
 * 同时该方法的返回值的类型是一个Class类，此类事Java反射的源头，实际上所谓反射从程序的运行结果来看也好理解，
 * 可以通过对象反射求出类的名称。
 *
 *
 * @Author EricGao
 * @Date 8:34 2020/6/20
 */

/**
 * 测试类的创建方式有哪些?
 * 方式一，通过对象获得 Class 对象
 * 方式二，通过forName获得
 * 方式三，通过类名.class 获得
 *
 * 有输出结果可知，不管哪种方式获得的Student的Class对象，都是同一个
 */
public class Reflection02 {
    public static void main(String[] args) throws ClassNotFoundException {
        Person person = new Student();
        System.out.println("person.name = " + person.name);  //output：person.name = 学生

        //方式一，通过对象获得 Class 对象
        Class<? extends Person> personClass = person.getClass();
        System.out.println("personClass.hashCode() = " + personClass.hashCode()); //output:  personClass.hashCode() = 356573597

        //方式二，通过forName获得
        Class<?> aClass = Class.forName("site.ericblog.reflection.Student");
        System.out.println("aClass.hashCode() = " + aClass.hashCode());  //output:  aClass.hashCode() = 356573597

        //方式三，通过类名.class 获得
        Class<Student> studentClass = Student.class;
        System.out.println("studentClass.hashCode() = " + studentClass.hashCode()); //output:  studentClass.hashCode() = 356573597

        /*------------------------------------------我是分割线-------------------------------------*/
        //方式四，基本 内置类型 的 包装类 都有一个Type属性
        Class<Integer> integerClass = Integer.TYPE;
        System.out.println(integerClass);  //output：  int

        //获得父类类型
        Class<? super Student> superclass = studentClass.getSuperclass();
        System.out.println("superclass = " + superclass); //output：  superclass = class site.ericblog.reflection.Person
    }
}

class Person{
    public String name; //避免使用 get和set 方法访问

    public Person(){}

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Student extends Person{
    public Student(){
        this.name = "学生";
    }
}

class Teacher extends Person{
    public Teacher(){
        this.name = "老师";
    }
}