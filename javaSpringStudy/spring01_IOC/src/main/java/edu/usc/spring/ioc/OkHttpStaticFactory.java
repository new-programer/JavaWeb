package edu.usc.spring.ioc;

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
