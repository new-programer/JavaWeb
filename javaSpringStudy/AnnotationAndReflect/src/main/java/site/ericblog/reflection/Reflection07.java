package site.ericblog.reflection;

/**
 * @Description 系统类的加载器相关测试
 * @Author EricGao
 * @Date 15:42 2020/6/20
 */
public class Reflection07 {
    public static void main(String[] args) throws ClassNotFoundException {

        //获取系统类的加载器 （AppClassLoader）
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("systemClassLoader = " + systemClassLoader); //输出：systemClassLoader = sun.misc.Launcher$AppClassLoader@18b4aac2

        //获取系统类加载器的父类加载器--->扩展类加载器 （ExtClassLoader）
        ClassLoader parent = systemClassLoader.getParent();
        System.out.println("parent = " + parent);//输出：parent = sun.misc.Launcher$ExtClassLoader@1540e19d

        //获取扩展类加载器的父类--->根加载器（C/C++）
        ClassLoader parentParent = parent.getParent();
        System.out.println("parentParent = " + parentParent);//输出：parentParent = null


        //测试当前类是哪个加载器加载的 （由输出结果可知，是AppClassLoader（系统类）加载器加载）
        ClassLoader classLoader = Class.forName("site.ericblog.reflection.Reflection07").getClassLoader();
        System.out.println("classLoader = " + classLoader); //输出：classLoader = sun.misc.Launcher$AppClassLoader@18b4aac2

        //测试 JDK内置类是哪个加载器加载的 （由输出结果可知，是根加载器加载（因为跟加载器获取不到，为null值））
        classLoader = Class.forName("java.lang.Object").getClassLoader();
        System.out.println("classLoader = " + classLoader); //输出：classLoader = null


        //如何获取系统类加载器可以加载的路径
        System.out.println("System.getProperty(\"java.class.path\") : \n" + System.getProperty("java.class.path"));
        //输出：
        /*
        * System.getProperty("java.class.path") :
        * D:\DepSoft\Java\jdk-8u121\jre\lib\charsets.jar;
        * D:\DepSoft\Java\jdk-8u121\jre\lib\deploy.jar;
        * D:\DepSoft\Java\jdk-8u121\jre\lib\ext\access-bridge-64.jar;
        * D:\DepSoft\Java\jdk-8u121\jre\lib\ext\cldrdata.jar;
        * D:\DepSoft\Java\jdk-8u121\jre\lib\ext\dnsns.jar;
        * D:\DepSoft\Java\jdk-8u121\jre\lib\ext\jaccess.jar;
        * D:\DepSoft\Java\jdk-8u121\jre\lib\ext\jfxrt.jar;
        * D:\DepSoft\Java\jdk-8u121\jre\lib\ext\localedata.jar;
        * D:\DepSoft\Java\jdk-8u121\jre\lib\ext\nashorn.jar;
        * D:\DepSoft\Java\jdk-8u121\jre\lib\ext\sunec.jar;
        * D:\DepSoft\Java\jdk-8u121\jre\lib\ext\sunjce_provider.jar;
        * D:\DepSoft\Java\jdk-8u121\jre\lib\ext\sunmscapi.jar;
        * D:\DepSoft\Java\jdk-8u121\jre\lib\ext\sunpkcs11.jar;
        * D:\DepSoft\Java\jdk-8u121\jre\lib\ext\zipfs.jar;
        * D:\DepSoft\Java\jdk-8u121\jre\lib\javaws.jar;
        * D:\DepSoft\Java\jdk-8u121\jre\lib\jce.jar;
        * D:\DepSoft\Java\jdk-8u121\jre\lib\jfr.jar;
        * D:\DepSoft\Java\jdk-8u121\jre\lib\jfxswt.jar;
        * D:\DepSoft\Java\jdk-8u121\jre\lib\jsse.jar;
        * D:\DepSoft\Java\jdk-8u121\jre\lib\management-agent.jar;
        * D:\DepSoft\Java\jdk-8u121\jre\lib\plugin.jar;
        * D:\DepSoft\Java\jdk-8u121\jre\lib\resources.jar;
        * D:\DepSoft\Java\jdk-8u121\jre\lib\rt.jar;
        * F:\JavaWeb\javaSpringStudy\AnnotationAndReflect\target\classes;
        * D:\Program Files\JetBrains\IntelliJ IDEA 2019.3\lib\idea_rt.jar*/



        //双亲委派机制：多重检测保证安全性，自己定义的类若和JDK内置的类一样，会忽略自定义的类，而且调用JDK内置的类

    }
}
