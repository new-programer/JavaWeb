package site.ericblog.reflection;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @Description 通过反射操作泛型
 * @Author EricGao
 * @Date 15:07 2020/6/23
 */
public class Reflection11 {
    public void test01(Map<String, User> map, List<User> list){
        System.out.println("test01");
    }

    public Map<String, User> test02(){
        System.out.println("test02");
        return null;
    }

    public static void main(String[] args) throws NoSuchMethodException {

        System.out.println("--------------------------------------test01方法---------------------------------------");

        Method method = Reflection11.class.getMethod("test01", Map.class, List.class);
        Type[] genericParameterTypes = method.getGenericParameterTypes();

        for (Type genericParameterType : genericParameterTypes) {

            System.out.println("----------------------------------我是分割线---------------------------------");

            System.out.println("genericParameterType = " + genericParameterType);//输出示例：genericParameterType = java.util.Map<java.lang.String, site.ericblog.reflection.User>
            if (genericParameterType instanceof ParameterizedType) {
                Type[] actualTypeArguments = ((ParameterizedType) genericParameterType).getActualTypeArguments();

                /*输出示例：
                * actualTypeArgument = class java.lang.String
                * actualTypeArgument = class site.ericblog.reflection.User
                * */
                for (Type actualTypeArgument : actualTypeArguments) {
                    System.out.println("actualTypeArgument = " + actualTypeArgument);
                }
            }

        }


        System.out.println("--------------------------------------test02方法---------------------------------------");
        Method method02 = Reflection11.class.getMethod("test02",null);
        Type genericReturnType = method02.getGenericReturnType();

        /**
         * 输出结果值：
         *
         * actualTypeArgument = class java.lang.String
         * actualTypeArgument = class site.ericblog.reflection.User
         *
         * 即返回了 String 和 User
         */
        if (genericReturnType instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();

            /*输出示例：
             * actualTypeArgument = class java.lang.String
             * actualTypeArgument = class site.ericblog.reflection.User
             * */
            for (Type actualTypeArgument : actualTypeArguments) {
                System.out.println("actualTypeArgument = " + actualTypeArgument);
            }
        }

    }
}
