package edu.usc.spring.ioc;

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
                System.out.println("response.body().string():\n" + response.body().string());
            }
        });
    }
}
