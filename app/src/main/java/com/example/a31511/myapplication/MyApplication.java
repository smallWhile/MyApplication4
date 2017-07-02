package com.example.a31511.myapplication;

import android.app.Application;

import com.example.a31511.myapplication.http.HttpHelper;
import com.example.a31511.myapplication.http.VolleyProcesser;
import com.example.a31511.myapplication.okhttp.OkhttpProcessor;

/**
 * Created by 31511 on 2017/6/20.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //HttpHelper.init(new VolleyProcesser(this));
        HttpHelper.init(new OkhttpProcessor());
    }
}
