package edu.usc.spring.ioc;

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
