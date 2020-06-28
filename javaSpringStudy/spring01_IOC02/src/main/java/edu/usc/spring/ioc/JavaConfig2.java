package edu.usc.spring.ioc;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * @Description Java和XML混合配置
 * @Author EricGao
 * @Date 21:56 2020/6/8
 */
@Configuration
@ImportResource("applicationContext.xml")
public class JavaConfig2 {

}
