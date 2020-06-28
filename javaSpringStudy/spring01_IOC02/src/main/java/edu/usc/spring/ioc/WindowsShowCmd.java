package edu.usc.spring.ioc;

/**
 * @Description TODO
 * @Author EricGao
 * @Date 19:30 2020/6/7
 */
public class WindowsShowCmd implements ShowCmd{

    public String showCmd(){
        return "dir";
    }
}
