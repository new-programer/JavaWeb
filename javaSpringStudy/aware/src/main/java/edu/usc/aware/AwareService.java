package edu.usc.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Description BeanNameAware 获取bean名称，其他根据awqre功能和名称相对应
 *
 * @Author EricGao
 * @Date 22:16 2020/6/8
 */
@Service
// @PropertySouce是spring3.1开始引入的基于java config的注解。
//通过 @PropertySource 注解将 properties 配置文件中的值存储到Spring的 Environment中，
// Environment 接口提供方法去读取配置文件中的值，参数是 properties文件中定义的key值。
@PropertySource(value = "usc.properties")
public class AwareService implements BeanNameAware, BeanFactoryAware, ResourceLoaderAware, EnvironmentAware {

    private String beanName;
    private ResourceLoader resourceLoader;
    private Environment environment;

    public void print() throws IOException {
        System.out.println("beanName = " + beanName);

        /*
        * Spring把其资源做了一个抽象，底层使用统一的资源访问接口来访问Spring的所有资源。也就是说，
        * 不管什么格式的文件，也不管文件在哪里，到Spring 底层，都只有一个访问接口，Resource。
        * */
        Resource resource = resourceLoader.getResource("usc.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));

        String s = br.readLine();
        System.out.println("s = " + s);
        br.close();

        /*通过 Environment 接口来读取 properties文件中定义的key值 */
        String address = environment.getProperty("usc.address");
        System.out.println("address = " + address);

    }

    /**
     *
     * @param beanFactory
     * @throws BeansException
     */
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }

    public void setBeanName(String s) {
        this.beanName = s;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
