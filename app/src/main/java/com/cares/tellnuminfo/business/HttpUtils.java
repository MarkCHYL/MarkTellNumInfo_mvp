package com.cares.tellnuminfo.business;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Desc
 * @作者 Mark
 * @时间 2019/1/22
 * @EMail 2285581945@qq.com
 */
public class HttpUtils {

    private String mUrl;
    private Map<String, String> mParam;
    private HttpResponse mHttpResponse;

    private final OkHttpClient client = new OkHttpClient();
    private Handler myHandler = new Handler(Looper.getMainLooper());

    public interface HttpResponse {
        void onSuccess(Object object);

        void onFailed(String error);
    }

    public HttpUtils(HttpResponse response) {
        this.mHttpResponse = response;
    }

    public void senfPostHttp(String url, Map<String, String> param) {
        sendHttp(url, param, true);
    }

    public void senfGetHttp(String url, Map<String, String> param) {
        sendHttp(url, param, false);
    }

    private void sendHttp(String url, Map<String, String> param, boolean isPost) {
        this.mUrl = url;
        this.mParam = param;
        //编写http请求逻辑
        run(isPost);
    }

    private void run(boolean isPost) {
        //创建Request请求
        Request request = createRequest(isPost);
        //创建请求队列
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                if (mHttpResponse != null) {
                    myHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mHttpResponse.onFailed("请求错误" + e.toString());
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    mHttpResponse.onFailed("请求失败：Code==》" + response.toString());
                } else {
                    myHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                mHttpResponse.onSuccess(response.body().string());
                                Log.d("网络请求返回==》", response.body().toString());
                            } catch (IOException e) {
                                myHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        mHttpResponse.onFailed("结果数据转换失败");
                                    }
                                });
                            }

                        }
                    });

                }
            }
        });
    }

    private Request createRequest(boolean isPost) {
        Request request;
        if (isPost) {
            MultipartBody.Builder requestBodyBuilder = new MultipartBody.Builder();
            requestBodyBuilder.setType(MultipartBody.FORM);
            //遍历map请求参数
            Iterator<Map.Entry<String, String>> iterator = mParam.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                requestBodyBuilder.addFormDataPart(entry.getKey(), entry.getValue());
            }
            request = new okhttp3.Request.Builder().url(mUrl)
                    .post(requestBodyBuilder.build()).build();
        } else {
            String urlStr = mUrl + "?" + getMapParamToString(mParam);
            Log.d("网络请求地址==》", urlStr);
            request = new Request.Builder().url(urlStr).build();
        }
        return request;
    }


    private String getMapParamToString(Map<String, String> param) {
        StringBuilder stringBuilder = new StringBuilder();
        //遍历map请求参数
        Iterator<Map.Entry<String, String>> iterator = mParam.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            stringBuilder.append(entry.getKey() + "=" + entry.getValue() + "&");
        }
        String str = stringBuilder.toString().substring(0, stringBuilder.length() - 1);
        Log.d("网络请求参数==》", str);
        return str;
    }
}
