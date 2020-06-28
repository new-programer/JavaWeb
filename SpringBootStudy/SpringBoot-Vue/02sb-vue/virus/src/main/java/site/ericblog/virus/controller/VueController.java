package site.ericblog.virus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description TODO
 * @Author EricGao
 * @Date 18:29 2020/6/27
 */

@Controller
public class VueController {
    /**
     * SpringBoot+Vue简单测试
     * @return String字符串
     */
    @RequestMapping("/vue.action")
    public String a(){
        return "a";
    }
}
