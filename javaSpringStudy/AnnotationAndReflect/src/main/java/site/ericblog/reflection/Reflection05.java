package site.ericblog.reflection;

/**
 * @Description 输出结果：
 *      A类的静态代码块初始化
 *      A类的无参构造器初始化
 *      A.m = 100
 *
 *  加载过程分析：
 *  m为静态变量，会先初始化0，为首先是静态代码块初始化（），其次是无参构造器初始化，然后是 m = 300, 最后是 m=100;
 *  即先等于了 300 再等于 100
 *
 *
 * @Author EricGao
 * @Date 11:09 2020/6/20
 */
public class Reflection05 {
    public static void main(String[] args) {
        A a = new A();  //2. new A()产生一个 A类的对象
        System.out.println("A.m = " + A.m);

        /**  真正的流程
         * 1.加载到内存，会产生一个对应的class对象
         * 2.链接，链接结束后 m = 0
         * 3.初识化，
         *      <clinit>(){
         *              System.out.println("A类的静态代码块初始化");
         *              m = 300;
         *              m = 100;
         *      }
         */
    }
}

class A{

    /**
     * 实际上，会把静态代码块和类变量赋值合并(即3和4)：
     *  m = 300;
     *  m = 100;
     */

    /**
     * 3. 初始化 静态代码块
     */
    static {
        System.out.println("A类的静态代码块初始化");
        m = 300;
    }

    /**
     * 4. m = 100;
     */
    static int m = 100;  //1. 初始化化m=0

    public A(){
        System.out.println("A类的无参构造器初始化");
    }
}