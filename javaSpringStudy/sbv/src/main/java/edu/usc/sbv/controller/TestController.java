package edu.usc.sbv.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author EricGao
 * @Date 17:08 2020/6/14
 */

@RestController
public class TestController {
    @RequestMapping("/test")
    public String test(){
        return "Test my TestController";
    }
}
