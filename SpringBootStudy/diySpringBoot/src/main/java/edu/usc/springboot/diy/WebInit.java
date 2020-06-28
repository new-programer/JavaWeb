package edu.usc.springboot.diy;

import edu.usc.springboot.diy.JavaConfig.SpringMVCConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @Description WebInit 的作用类似于 web.xml，这个类需要实现 WebApplicationInitializer 接口，并实现接口中的方法。
 * 当项目启动时，onStartup 方法会被自动执行，我们可以在这个方法中做一些项目初始化操作，例如加载 SpringMVC 容器，
 * 添加过滤器，添加 Listener、添加 Servlet 等。
 *
 * 注意：
 * 由于我们在 WebInit 中只是添加了 SpringMVC 的配置，这样项目在启动时只会去加载 SpringMVC 容器，
 * 而不会去加载 Spring 容器，如果一定要加载 Spring 容器，需要我们修改 SpringMVC 的配置，
 * 在 SpringMVC 配置的包扫描中也去扫描 @Configuration 注解，进而加载 Spring 容器，还有一种方案可以解决这个问题，
 * 就是直接在项目中舍弃 Spring 配置，直接将所有配置放到 SpringMVC 的配置中来完成，这个在 SSM 整合时是没有问题的，
 * 在实际开发中，较多采用第二种方案，第二种方案，SpringMVC 的配置如下：
 * @Configuration
 * @ComponentScan(basePackages = "org.javaboy")
 * public class SpringMVCConfig {
 * }
 *
 * @Author EricGao
 * @Date 17:58 2020/6/10
 */
public class WebInit implements WebApplicationInitializer {
    public void onStartup(ServletContext servletContext) throws ServletException {
        //首先加载 SpringMVC 的配置文件
        AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext();
        acac.register(SpringMVCConfig.class);

        // 添加 DispatcherServlet
        ServletRegistration.Dynamic springmvc = servletContext.addServlet("springmvc", new DispatcherServlet());
        // 给 DispatcherServlet 添加路径映射
        springmvc.addMapping("/");
        // 给 DispatcherServlet 添加启动时机
        springmvc.setLoadOnStartup(1);
    }
}
