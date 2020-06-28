package site.ericblog.sbootvuefirst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import site.ericblog.sbootvuefirst.pojo.Person;
import site.ericblog.sbootvuefirst.service.PersonService;

import java.util.List;

/**
 * @Description 个人信息交互
 * @Author EricGao
 * @Date 13:34 2020/6/27
 */
@RestController
public class PersonController {
    @Autowired
    private PersonService service;

    @GetMapping(value = "findAll")
    public List<Person> findAll(){
        List<Person> list = service.findAll();

        return list;
    }
}
