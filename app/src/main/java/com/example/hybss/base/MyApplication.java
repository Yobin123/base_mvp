package com.example.hybss.base;

import android.app.Application;

/**
 * @Author: yobin he
 * @Date:2019/8/19 10:52
 * @Email: heyibin@huawenpicture.com
 * @Des :
 */
public class MyApplication extends Application {
    public static MyApplication app;

    public static MyApplication getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
}
