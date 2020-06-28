package site.ericblog.reflection;

import java.lang.annotation.*;
import java.lang.reflect.Field;

/**
 * @Description 通过反射操作注解
 * @Author EricGao
 * @Date 15:36 2020/6/23
 */
public class Reflection12 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        //获取class对象
        Class<?> c1 = Class.forName("site.ericblog.reflection.Student2");

        //通过反射获得注解
        Annotation[] annotations = c1.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println("annotation = " + annotation); //输出结果：annotation = @site.ericblog.reflection.TableEric(value=db_student)
        }

        //获得注解的 value 的值(通过指定注解的方式进行获取)
        TableEric tableEric = c1.getAnnotation(TableEric.class);
        System.out.println("tableEric.value() = " + tableEric.value()); //输出结果：tableEric.value() = db_student


        //获得类指定的注解
        Field field = c1.getDeclaredField("name");
        FieldEric fieldEric = field.getAnnotation(FieldEric.class);
        System.out.println("fieldEric.columnName() = " + fieldEric.columnName()); //输出结果：fieldEric.columnName() = db_name
        System.out.println("fieldEric.type() = " + fieldEric.type()); //输出结果：fieldEric.type() = varchar
        System.out.println("fieldEric.length() = " + fieldEric.length()); //输出结果：fieldEric.length() = 3
    }
}

@TableEric("db_student")
class Student2{
    @FieldEric(columnName = "db_id", type = "int", length = 10)
    private int id;
    @FieldEric(columnName = "db_age", type = "int", length = 10)
    private int age;
    @FieldEric(columnName = "db_name", type = "varchar", length = 3)
    private String name;

    public Student2() {
    }

    public Student2(int id, int age, String name) {
        this.id = id;
        this.age = age;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student2{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

/**
 * 定义一个类名的注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface TableEric{
    String value();
}


/**
 * 定义一个属性的注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface FieldEric{
    String columnName();
    String type();
    int length();
}