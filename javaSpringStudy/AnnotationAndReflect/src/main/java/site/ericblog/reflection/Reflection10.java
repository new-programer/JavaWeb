package site.ericblog.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description 分析性能问题（反射方式和普通方式）
 * 输出结果：
 * 普通方式调用(执行10亿次)--->(endTime-startTime) = 3
 * 反射方式调用(执行10亿次)--->(endTime-startTime) = 1801
 * 反射方式调用-关闭检测(执行10亿次)--->(endTime-startTime) = 1211
 *
 * 由以上输出结果可知，和普通调用相比反射性能太低，虽然关闭了检测提升了一下反射的性能，但还是低
 * 如无必要，应避免使用反射
 * @Author EricGao
 * @Date 14:50 2020/6/23
 */
public class Reflection10 {

    //普通方式调用
    public static void test01(){
        User user = new User();

        long startTime = System.currentTimeMillis();

        /*测试代码区间*/
        for (int i = 0; i < 1000000000; i++) {
            user.getName();
        }

        long endTime = System.currentTimeMillis();

        System.out.print("普通方式调用(执行10亿次)--->");
        System.out.println("(endTime-startTime) = " + (endTime - startTime));
    }

    //反射方式调用
    public static void test02() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User user = new User();
        Class c1 = user.getClass();
        Method getName = c1.getDeclaredMethod("getName", null);

        long startTime = System.currentTimeMillis();

        /*测试代码区间*/
        for (int i = 0; i < 1000000000; i++) {
            getName.invoke(user, null); //通过反射的方式去执行
        }

        long endTime = System.currentTimeMillis();

        System.out.print("反射方式调用(执行10亿次)--->");
        System.out.println("(endTime-startTime) = " + (endTime - startTime));
    }

    //反射方式调用 关闭检测
    public static void test03() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User user = new User();
        Class c1 = user.getClass();
        Method getName = c1.getDeclaredMethod("getName", null);

        getName.setAccessible(true);

        long startTime = System.currentTimeMillis();

        /*测试代码区间*/
        for (int i = 0; i < 1000000000; i++) {
            getName.invoke(user, null); //通过反射的方式去执行
        }

        long endTime = System.currentTimeMillis();

        System.out.print("反射方式调用-关闭检测(执行10亿次)--->");
        System.out.println("(endTime-startTime) = " + (endTime - startTime));
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        test01();
        test02();
        test03();
    }
}
