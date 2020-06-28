### spring_IOC之静态工厂注入和实例工厂注入

#### 用到的额外的库（okhttp）
maven配置
```xml
    <dependency>
        <groupId>com.squareup.okhttp3</groupId>
        <artifactId>okhttp</artifactId>
        <version>4.7.2</version>
    </dependency>
```

#### 静态工厂类
```java
package edu.usc.spring.ioc.model;

import okhttp3.OkHttpClient;

/**
 * @Description  静态工厂
 * @Author EricGao
 * @Date 19:15 2020/6/6
 */

/**
 * 使用到了单例模式
 */
public class OkHttpStaticFactory {
    private static OkHttpClient okHttpClient;

    private OkHttpStaticFactory(){}

    public static OkHttpClient getInstance(){
        if (okHttpClient == null){
            return new OkHttpClient.Builder().build();
        }
        return okHttpClient;
    }
}

```

#### 实例工厂类
```java
package edu.usc.spring.ioc.model;

import okhttp3.OkHttpClient;

/**
 * @Description 实例工厂
 * @Author EricGao
 * @Date 19:27 2020/6/6
 */
public class OkHttpFactory {
    private OkHttpClient okHttpClient;

    public OkHttpClient getInstance(){
        if (okHttpClient == null){
            okHttpClient = new OkHttpClient.Builder().build();
        }
        return okHttpClient;
    }
}

```

#### xml配置(静态工厂注入和实例工厂注入)
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:p="http://www.springframework.org/schema/p"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--静态工厂注入：将从OkHttpStaticFactory类中得到的OkHttpClient 添加到spring容器-->
<!--    <bean class="edu.usc.spring.ioc.model.OkHttpStaticFactory" factory-method="getInstance" id="okHttpClient" />-->


    <!--实例工厂注入： 将从OkHttpFactory类中得到的OkHttpClient 添加到spring容器-->
        <!--1.先得到 实例工厂 的实例 okHttpFactory-->
    <bean class="edu.usc.spring.ioc.model.OkHttpFactory" id="okHttpFactory" />
        <!--2.获得 实例工厂 的实例 okHttpFactory 后，再通过该实例获得 okHttpClient-->
    <bean class="okhttp3.OkHttpClient" factory-bean="okHttpFactory" factory-method="getInstance" id="okHttpClient"/>
</beans>
```


#### OkHttp测试类

```java
package edu.usc.spring.ioc.model;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @Description 通过 okhttp 库来进行网络通信流的测试
 * @Author EricGao
 * @Date 19:05 2020/6/6
 */
public class OkHttpTest {
    public static void main(String[] args) {
//        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        ClassPathXmlApplicationContext cpxac = new ClassPathXmlApplicationContext("applicationContext.xml");
        OkHttpClient okHttpClient = cpxac.getBean("okHttpClient", OkHttpClient.class);

        Request request = new Request.Builder().get().url("http://www.baidu.com").build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.getMessage();
            }

            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                System.out.println("response.body().string():" + response.body().string());
            }
        });
    }
}

```

#### 输出结果
```text
response.body().string():
<!DOCTYPE html>
<!--STATUS OK--><html> <head><meta http-equiv=content-type content=text/html;charset=utf-8><meta http-equiv=X-UA-Compatible content=IE=Edge><meta content=always name=referrer><link rel=stylesheet type=text/css href=http://s1.bdstatic.com/r/www/cache/bdorz/baidu.min.css><title>百度一下，你就知道</title></head> <body link=#0000cc> <div id=wrapper> <div id=head> <div class=head_wrapper> <div class=s_form> <div class=s_form_wrapper> <div id=lg> <img hidefocus=true src=//www.baidu.com/img/bd_logo1.png width=270 height=129> </div> <form id=form name=f action=//www.baidu.com/s class=fm> <input type=hidden name=bdorz_come value=1> <input type=hidden name=ie value=utf-8> <input type=hidden name=f value=8> <input type=hidden name=rsv_bp value=1> <input type=hidden name=rsv_idx value=1> <input type=hidden name=tn value=baidu><span class="bg s_ipt_wr"><input id=kw name=wd class=s_ipt value maxlength=255 autocomplete=off autofocus></span><span class="bg s_btn_wr"><input type=submit id=su value=百度一下 class="bg s_btn"></span> </form> </div> </div> <div id=u1> <a href=http://news.baidu.com name=tj_trnews class=mnav>新闻</a> <a href=http://www.hao123.com name=tj_trhao123 class=mnav>hao123</a> <a href=http://map.baidu.com name=tj_trmap class=mnav>地图</a> <a href=http://v.baidu.com name=tj_trvideo class=mnav>视频</a> <a href=http://tieba.baidu.com name=tj_trtieba class=mnav>贴吧</a> <noscript> <a href=http://www.baidu.com/bdorz/login.gif?login&amp;tpl=mn&amp;u=http%3A%2F%2Fwww.baidu.com%2f%3fbdorz_come%3d1 name=tj_login class=lb>登录</a> </noscript> <script>document.write('<a href="http://www.baidu.com/bdorz/login.gif?login&tpl=mn&u='+ encodeURIComponent(window.location.href+ (window.location.search === "" ? "?" : "&")+ "bdorz_come=1")+ '" name="tj_login" class="lb">登录</a>');</script> <a href=//www.baidu.com/more/ name=tj_briicon class=bri style="display: block;">更多产品</a> </div> </div> </div> <div id=ftCon> <div id=ftConw> <p id=lh> <a href=http://home.baidu.com>关于百度</a> <a href=http://ir.baidu.com>About Baidu</a> </p> <p id=cp>&copy;2017&nbsp;Baidu&nbsp;<a href=http://www.baidu.com/duty/>使用百度前必读</a>&nbsp; <a href=http://jianyi.baidu.com/ class=cp-feedback>意见反馈</a>&nbsp;京ICP证030173号&nbsp; <img src=//www.baidu.com/img/gs.gif> </p> </div> </div> </div> </body> </html>

```

