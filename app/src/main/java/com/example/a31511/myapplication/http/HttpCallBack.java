package com.example.a31511.myapplication.http;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by 31511 on 2017/6/20.
 */

public abstract class HttpCallBack<Result> implements ICallBack {
    @Override
    public void onSuccess(String result) {

        Gson gson = new Gson();
        Class<?> clz = analysisClassInfo(this);
        Result objResult = (Result) gson.fromJson(result, clz);
        onSuccess(objResult);
    }


    public abstract void onSuccess(Result result);

    public Class<?> analysisClassInfo(Object o) {
        Type genType = o.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return (Class<?>) params[0];
    }

}
