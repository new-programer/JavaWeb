package edu.usc.spring.ioc;

import edu.usc.spring.ioc.model.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @Description TODO
 * @Author EricGao
 * @Date 12:13 2020/6/6
 */
public class Main {
    public static void main(String[] args) {
//        User user = new User();
//        System.out.println("user: "+user);

        /*加载spring的xml配置文件,获取创建ClassPathXmlApplicationContext对象的
        过程（new ClassPathXmlApplicationContext("applicationContext.xml")），会对User类进行初始化
        初始化之后，User的实例被放在spring容器里面，若需要这个实例，直接从spring容器里面获取即可*/
        ClassPathXmlApplicationContext cpxac = new ClassPathXmlApplicationContext("testContext.xml");  //为主，常用
//        FileSystemXmlApplicationContext fsxac = new FileSystemXmlApplicationContext("applicationContext.xml文件的绝对路径"); //一般不用

        GetAndOutputInstance(cpxac);

        /*输出结果如下：
            * 已被User被初始化-------------------说明创建对象的时候User实例被初始化
            * user1 = edu.usc.spring.ioc.model.User@464bee09
            * user2 = edu.usc.spring.ioc.model.User@464bee09
            * user3 = edu.usc.spring.ioc.model.User@464bee09
            * 以上三个User的实例的是一样的，说明，以上三种方式获取的User实例都是同一个
            * */

    }

    /**
     *获取并输出User实例的内容
     * @param cpxac 用于传递 spring 容器对象引用
     */
    private static void GetAndOutputInstance(ClassPathXmlApplicationContext cpxac) {
        /*从spring容器中获取User实例的方式*/
        //1.通过名称方式获取
        User user = (User) cpxac.getBean("user");  //从spring容器获取User实例方式一
        //2.输出
        System.out.println("user = " + user);

    }
}
