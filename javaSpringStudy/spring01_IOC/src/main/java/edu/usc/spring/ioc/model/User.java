package edu.usc.spring.ioc.model;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Description TODO
 * @Author EricGao
 * @Date 12:13 2020/6/6
 */
public class User {
    private String username;
    private String address;
    private Integer id;

    private Cat cat;
    private Cat[] cats;
    private List<String> favorites;
    private Map<String, Object> details; //这里写成Object 方便在注入的时候接受任何类型的值
    private Properties info;

    public User(){
    }



    /*重写构造方法，方便注入*/
    public User(String username, String address, Integer id) {
        this.username = username;
        this.address = address;
        this.id = id;
    }




    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        System.out.println("username = " + username);
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public Cat[] getCats() {
        return cats;
    }

    public void setCats(Cat[] cats) {
        this.cats = cats;
    }

    public List<String> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<String> favorites) {
        this.favorites = favorites;
    }

    public Map<String, Object> getDetails() {
        return details;
    }

    public void setDetails(Map<String, Object> details) {
        this.details = details;
    }

    public Properties getInfo() {
        return info;
    }

    public void setInfo(Properties info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", id=" + id +
                ", cat=" + cat +
                ", cats=" + Arrays.toString(cats) +
                ", favorites=" + favorites +
                ", details=" + details +
                ", info=" + info +
                '}';
    }
}
