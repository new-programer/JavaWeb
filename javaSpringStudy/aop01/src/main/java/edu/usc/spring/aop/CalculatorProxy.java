package edu.usc.spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description TODO
 * @Author EricGao
 * @Date 10:09 2020/6/9
 */
public class CalculatorProxy {
     public static Object getInstance(final MyCalculatorImp myCalculatorImp){
         /**
          *该方法接受的参数依次为：类加载器classloader， 代理多项实现的接口，代理对象方法的处理器。
          * 注：所有额外添加的行为都在 invoke 方法中实现
          */
        return Proxy.newProxyInstance(CalculatorProxy.class.getClassLoader(),
                myCalculatorImp.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     *invoke 方法的完成扩展功能（即增强功能）
                     * @param proxy 代理对象
                     * @param method 代理的方法
                     * @param args 方法参数
                     * @return 方法返回值
                     * @throws Throwable
                     */
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method.getName() + ": 方法开始执行");
                Object invoke = method.invoke(myCalculatorImp, args); //args为 调用 add 方法传来的参数
                System.out.println(method.getName() + ": 方法执行结束");
                return invoke;
            }
        });
     }
}
