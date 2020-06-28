package site.ericblog.reflection;

import java.lang.annotation.ElementType;

/**
 * @Description 所有类型的Class
 * @Author EricGao
 * @Date 10:03 2020/6/20
 */
public class Reflection03 {
    public static void main(String[] args) {
        Class<Object> objectClass = Object.class; //类
        Class<Comparable> comparableClass = Comparable.class; //接口
        Class<String[]> stringArrClass = String[].class; //一维数组
        Class<int[][]> intArrClass = int[][].class; //二维数组
        Class<Override> overrideClass = Override.class; //注解
        Class<ElementType> elementTypeClass = ElementType.class; //枚举
        Class<Integer> integerClass = Integer.class;// 基本数据类
        Class<Void> voidClass = void.class;  // void
        Class<Class> classClass = Class.class; //Class

        System.out.println("objectClass = " + objectClass); //output : objectClass = class java.lang.Object
        System.out.println("comparableClass = " + comparableClass); //output : comparableClass = interface java.lang.Comparable
        System.out.println("stringArrClass = " + stringArrClass); //output : stringArrClass = class [Ljava.lang.String;
        System.out.println("intArrClass = " + intArrClass); //output : intArrClass = class [[I
        System.out.println("overrideClass = " + overrideClass); //output : overrideClass = interface java.lang.Override
        System.out.println("elementTypeClass = " + elementTypeClass); //output : elementTypeClass = class java.lang.annotation.ElementType
        System.out.println("integerClass = " + integerClass); //output : integerClass = class java.lang.Integer
        System.out.println("voidClass = " + voidClass); //output : voidClass = void
        System.out.println("classClass = " + classClass); //output : classClass = class java.lang.Class


        /*只有元素类型和维度一样，就对应同一个Class对象*/
        int[] a = new int[10];
        int[] b = new int[100];
        System.out.println("a.getClass().hashCode() = " + a.getClass().hashCode()); //output : a.getClass().hashCode() = 356573597
        System.out.println("b.getClass().hashCode() = " + b.getClass().hashCode()); //output : b.getClass().hashCode() = 356573597
    }
}
