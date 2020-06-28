package site.ericblog.sbootvuefirst.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import site.ericblog.sbootvuefirst.pojo.Person;

import java.util.List;

/**
 * @Description
 * 个人信息Mapper，此处的Mapper注解会被启动类的@MapperScan扫描到，
 * 导入 mybatis 依赖，@Mapper 注解才会生效
 * @Author EricGao
 * @Date 13:06 2020/6/27
 */

@Mapper
public interface PersonMapper {
    /**
     * 查询所有的用户
     * @return List<Person>
     */
    List<Person> findAll();
}
