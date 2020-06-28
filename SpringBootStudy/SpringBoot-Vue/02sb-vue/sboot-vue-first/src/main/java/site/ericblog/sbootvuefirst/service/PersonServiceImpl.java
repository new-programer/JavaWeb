package site.ericblog.sbootvuefirst.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.ericblog.sbootvuefirst.mapper.PersonMapper;
import site.ericblog.sbootvuefirst.pojo.Person;

import java.util.List;

/**
 * @Description TODO
 * @Author EricGao
 * @Date 13:32 2020/6/27
 */
@Service
public class PersonServiceImpl implements PersonService{
    @Autowired(required = false)
    private PersonMapper mapper;

    @Override
    public List<Person> findAll() {
        return null;
    }
}
