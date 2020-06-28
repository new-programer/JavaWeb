package site.ericblog.reflection;

/**
 * @Description 测试类什么时候会被初始化
 * @Author EricGao
 * @Date 14:28 2020/6/20
 */
public class Reflection06 {
    static {
        System.out.println("main类被加载");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        //1.主动引用
//        Son son = new Son();
        /*输出结果：
        * main类被加载
        * 父类被加载
        * 子类被加载
        * */


        //方式也会产生主动引用
//        Class.forName("site.ericblog.reflection.Son");
        /*输出结果：
         * main类被加载
         * 父类被加载
         * 子类被加载
         * */

        //不会产生类引用的方法
//        System.out.println("Son.b = " + Son.b);
        /*输出结果：
         * main类被加载
         * 父类被加载
         * Son.b = 2
         *
         * 故验证了：
         * 当访问一个静态域时，只有**真正**声明这个域的类才会被初始化。如：当通过子类引用父类的静态变量，
         *不会导致子类初始化。
         * */



//        Son[] array = new Son[5];
        /*输出结果：
         * main类被加载
         *
         * 故验证了：通过数组定义类引用，不会触发此类的初始化
         * */


        System.out.println(Son.M);
        /*输出结果：
         * main类被加载
         * 1
         *
         * 故验证了：引用常量 不会触发此类的初始化（常量在链接阶段就存入调用类的常量池中了）
         * */
    }
}

class Father{
    public static int b = 2;
    static {
        System.out.println("父类被加载");
    }
}

class Son extends Father{
    static {
        System.out.println("子类被加载");
        m = 300;
    }

    static int m = 100;
    static final int M = 1;
}