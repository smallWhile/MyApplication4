package com.example.a31511.myapplication.http;

import java.util.Map;

/**
 * Created by 31511 on 2017/6/20.
 */

public interface IHttpProcessor {


    void post(String url, Map<String, Object> params, ICallBack callBack);

    void get(String url, Map<String, Object> params, ICallBack callBack);
}
