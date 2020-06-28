package edu.usc.spring.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.security.spec.DSAGenParameterSpec;

/**
 * @Description TODO
 * @Author EricGao
 * @Date 19:35 2020/6/7
 */

@Configuration
public class JavaConfig {
    @Bean("cmd")
    @Conditional(WindowsCondition.class)
    ShowCmd winCmd(){
        return new WindowsShowCmd();
    }

    @Bean("cmd")
    @Conditional(LinuxCondition.class)
    ShowCmd linuxCmd() {
        return new LinuxShowCmd();
    }

    /*配置注入 DataSource*/
    @Bean
    @Profile("dev")  //开发环境
    DataSource devDs(){
        DataSource ds = new DataSource();
        ds.setUrl("1111url");
        ds.setUsername("root");
        ds.setPassword("123");
        return ds;
    }

    @Bean
    @Profile("prod") //生产环境
    DataSource proDs(){
        DataSource ds = new DataSource();
        ds.setUrl("2222url");
        ds.setUsername("root");
        ds.setPassword("1111");
        return ds;
    }
}
