package edu.usc.springboot.diy.Controller;

import edu.usc.springboot.diy.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author EricGao
 * @Date 19:21 2020/6/10
 */

@RestController
public class Controller {
    @Autowired
    HelloService helloService;
    @GetMapping("/hello")
    public String hello() {
        return helloService.hello();
    }
}
