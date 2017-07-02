package com.example.a31511.myapplication.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 31511 on 2017/6/20.
 */

public class HttpHelper implements IHttpProcessor {

    private static IHttpProcessor mIHttpProcessor = null;
    private Map<String, Object> mParams;
    private static HttpHelper _HttpHelper;

    private HttpHelper() {
        mParams = new HashMap<>();
    }

    public static HttpHelper obtain() {
        synchronized (HttpHelper.class) {
            if (_HttpHelper == null) {
                _HttpHelper = new HttpHelper();
            }
        }
        return _HttpHelper;
    }

    public static void init(IHttpProcessor iHttpProcessor) {
        mIHttpProcessor = iHttpProcessor;
    }

    @Override
    public void post(String url, Map<String, Object> params, ICallBack callBack) {

        final String filnalurl = appendParams(url, params);
        mIHttpProcessor.post(url, params, callBack);
    }

    @Override
    public void get(String url, Map<String, Object> params, ICallBack callBack) {
        final String filnalurl = appendParams(url, params);
        mIHttpProcessor.get(url, params, callBack);
    }

    public static String appendParams(String url, Map<String, Object> params) {
        if (params == null || params.isEmpty()) {
            return url;
        }

        StringBuilder urlBuilder = new StringBuilder();
        if (urlBuilder.indexOf("?") <= 0) {
            urlBuilder.append("?");
        } else {
            if (urlBuilder.toString().equals("?")) {
                urlBuilder.append("&");
            }
        }
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            urlBuilder.append(entry.getKey()).append("=").append(encode(entry.getValue().toString()));
        }
        return urlBuilder.toString();
    }

    public static String encode(String str) {

        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
