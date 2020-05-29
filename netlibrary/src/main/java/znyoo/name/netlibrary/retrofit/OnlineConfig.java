package znyoo.name.netlibrary.retrofit;


import android.app.Dialog;

import com.blankj.utilcode.util.LogUtils;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.parkingwang.okhttp3.LogInterceptor.LogInterceptor;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author wangdh
 * @time 2019/5/15 14:56
 * @describe 偏向于全局配置的 设置
 */
public class OnlineConfig {
    //是否显示框架内部日志
    public boolean isLog = true;
    public String url = "";
    public int time_out = 10000;

    public boolean isShowWait = false;
    public Dialog waitDialog;

    public boolean isShowError = false;
    public boolean isErrorToast = false;
    public Dialog ErrorDialog;


    public static OnlineConfig getDefConfig() {
        return new OnlineConfig();
    }

    public Retrofit retrofit;

    //按照默认配置初始化 Retrofit
    public void build() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(this.time_out, TimeUnit.SECONDS);
        if (this.isLog) {
            okHttpClient.addInterceptor(new LogInterceptor());
        }

        /**
         * 对所有请求添加请求头
         */
//        okHttpClient.addInterceptor(new Interceptor() {
//            @Override
//            public okhttp3.Response intercept(Chain chain) throws IOException {
//                Request request = chain.request();
//                okhttp3.Response originalResponse = chain.proceed(request);
//                return originalResponse.newBuilder().header("key1", "value1").addHeader("key2", "value2").build();
//            }
//        });

        retrofit = new Retrofit.Builder()
                .client(okHttpClient
                        .addInterceptor(new RequestEncryptInterceptor())
                        .addInterceptor(new ResponseDecryptInterceptor())
                        .build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(this.url)
                .build();
    }

    //加密拦截器
    class RequestEncryptInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            MediaType JSON = MediaType
                    .parse("application/json; charset=utf-8");
            Charset charset = Charset.forName("UTF-8");
            Request request = chain.request();
            String method = request.method().toLowerCase().trim();
            try {
                RequestBody requestBody = request.body();
                charset = JSON.charset(charset);

                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);
                String requestData = URLDecoder.decode(buffer.readString(charset).trim(), "utf-8");
                org.json.JSONObject obj = new org.json.JSONObject(requestData);
                String mName = obj.getString("method");

                String KEY;
                //加解密
//                if (UrlConstantKt.ADDORDER.equals(mName)
//                        || UrlConstantKt.GETORDER.equals(mName)
//                        || UrlConstantKt.UPDATEORDERSTATUS.equals(mName)
//                ) {
//                    KEY = (String) SPUtil.get(App.Companion.getInstace(), "key", CommonSets.KEY);
//                } else {
//                    KEY = CommonSets.KEY;
//                }
//                /*构建新的请求体*/
//                RequestBody newRequestBody = RequestBody.create(JSON, CommonMethodKt.paramsMethod((JSONObject) JSONObject.parse(requestData), KEY));

                /*构建新的requestBuilder*/
                Request.Builder newRequestBuilder = request.newBuilder();
                //根据请求方式构建相应的请求
                switch (method) {
                    case "post":
                        newRequestBuilder.post(requestBody);
                        break;

                    case "put":
                        newRequestBuilder.put(requestBody);
                        break;
                }
                request = newRequestBuilder.build();
            } catch (Exception e) {
                LogUtils.e("加密失败!!!");
                chain.proceed(request);
            }
            return chain.proceed(request);
        }
    }

    //解密拦截器
    class ResponseDecryptInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response proceed = chain.proceed(request);
            if (proceed.isSuccessful()) {
                try {
                    ResponseBody body = proceed.body();

                    BufferedSource source = body.source();
                    source.request(Long.MAX_VALUE);
                    Buffer buffer = source.buffer();

                    Charset charset = Charset.forName("UTF-8");
                    MediaType mediaType = body.contentType();
                    if (mediaType != null) {
                        charset = mediaType.charset(charset);
                    }
                    //获取返回数据
                    String data = buffer.clone().readString(charset);
                    LogUtils.e("Response: " + data);

//                    //获取加密result
//                    String result = commonPaseParams(data);
//                    //获取sign
//                    String sign = commonPaseSign(data);
//                    //解密
//                    data = UseRSAUtil.decryptData(result);

                    LogUtils.e("Decrypt: " + data);
                    ResponseBody responseBody = ResponseBody.create(mediaType, data.trim());

                    proceed = proceed.newBuilder().body(responseBody).build();
                } catch (Exception e) {
                    LogUtils.e("解密失败!!!");
                    return proceed;
                }
            }
            return proceed;
        }
    }

}
