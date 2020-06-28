package edu.usc.springboot.diy.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author EricGao
 * @Date 19:21 2020/6/10
 */

@Service
public class HelloService {
    public String hello() {
        return "hello";
    }
}
