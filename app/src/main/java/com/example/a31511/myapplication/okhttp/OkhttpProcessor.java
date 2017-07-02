package com.example.a31511.myapplication.okhttp;

import android.os.Handler;

import com.example.a31511.myapplication.http.ICallBack;
import com.example.a31511.myapplication.http.IHttpProcessor;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 31511 on 2017/6/20.
 */

public class OkhttpProcessor implements IHttpProcessor {
    private OkHttpClient mOkHttpClient;
    private Handler myHandler;

    public OkhttpProcessor() {
        mOkHttpClient = new OkHttpClient();
        myHandler = new Handler();
    }

    @Override
    public void post(String url, Map<String, Object> params, final ICallBack callBack) {

        RequestBody requestBody = appendBody(params);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {

                myHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFailure(e.toString());
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    final String result = response.body().string();
                    myHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onSuccess(result);
                        }
                    });

                }
            }
        });
    }

    @Override
    public void get(String url, Map<String, Object> params, final ICallBack callBack) {
        // RequestBody requestBody = appendBody(params);
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {

                myHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFailure(e.toString());
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    final String result = response.body().string();
                    myHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onSuccess(result);
                        }
                    });
                }
            }
        });
    }

    private RequestBody appendBody(Map<String, Object> params) {
        FormBody.Builder body = new FormBody.Builder();
        if (params == null || params.isEmpty()) {
            return body.build();
        }
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            body.add(entry.getKey(), entry.getValue().toString());
        }
        return body.build();
    }
}
