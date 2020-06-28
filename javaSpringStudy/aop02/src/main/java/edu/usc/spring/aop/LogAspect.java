package edu.usc.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author EricGao
 * @Date 10:51 2020/6/9
 */
@Component
@Aspect //表示 LogAspect 是一个切面
public class LogAspect {

    /**
     *pointcut中 统一定义切点，减少冗余。尽管省了很多事，但其和之前的一样还是侵入式的(
     * 每个方法如add和min前面都需要分别加上 @Action 注解)
     */
    @Pointcut("@annotation(Action)")
    public void pointcut(){

    }


    /**
     * 和上面的不同，这个统一定义的切点，是非侵入式的，即不需要在 add和min方法前面加 @Action注解了
     * 其中：
     * 第一个 * 表示任意返回值类型，也可以指定具体的返回值类型
     * 第二个 * 表示 service 包下的任意类，
     * 第三个 * 表示 service 包下的任意类的任意方法， 两点 .. 表示该方法的任意参数，
     */
    @Pointcut("execution(* edu.usc.spring.aop.service.*.*(..))")
    public void pointcut_exec(){

    }

    /**
     * 前置通知：目标方法执行之前的通知 ，
     * @param joinPoint
     */
//    @Before("@annotation(Action)")
    @Before(value = "pointcut()")
    public void before (JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        System.out.println(name + "方法开始执行了。。。");
    }


    /**后前置通知：目标方法执行之后的通知 ，
     * @param joinPoint
     */
//    @After("@annotation(Action)")
    @After(value = "pointcut()")
    public void after(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        System.out.println(name + "方法执行结束了。。。。。。。。" );
    }


    /**
     * 返回通知,如果目标方法的返回值为void，则收到 null
     * @param joinPoint 切点
     * @param r 返回值名称，只有当返回值类型为Object的时候，所有方法会相应返回通知
     *          不然，r的类型要和相应的方法返回值保持一致才能具有返回通知，如add 方法的返回值为Integer类型
     *          但是此处r的类型为 String 类型的话，add方法执行的时候不会触发放回通知的
     */
//    @AfterReturning(value = "@annotation(Action)", returning = "r")
    @AfterReturning(value = "pointcut_exec()", returning = "r")
    public void returning(JoinPoint joinPoint, Object r){
        String name = joinPoint.getSignature().getName();
        System.out.println(name + "方法返回通知：" + r);
    }


    /**
     * 异常通知， 当目标方法跑出异常时， 该方法会被触发
     * @param joinPoint
     * @param e 异常参数， 和方法参数名一一对应，注意异常的类型，和返回通知类似，异常类型需要匹配才能触发异常通知
     */
//    @AfterThrowing(value = "@annotation(Action)", throwing = "e")
    @AfterThrowing(value = "pointcut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e){
        String name = joinPoint.getSignature().getName();
        System.out.println(name + "方法异常通知：" + e.getMessage());
    }


    /**
     * 环绕通知， 是上面其他四种通知的集大成者，环绕通知的核心类似于在反射中执行方法
     * @param pjp
     * @return
     */
//    @Around("@annotation(Action)")
    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //类似于 method.invoke 方法，可以在该方法的前后分别添加日志，相当如前置/后置通知

        /*输出：
        * add方法开始执行了。。。
        * 100+100=200 （注意这两不再是， 3+7=10了）
         * */
//        Object proceed = pjp.proceed(new Object[]{5, 5});
        Object proceed = pjp.proceed();
        return proceed;
    }
}
