package znyoo.name.netlibrary.retrofit

import com.parkingwang.okhttp3.LogInterceptor.LogInterceptor
import okhttp3.OkHttpClient
import znyoo.name.netlibrary.retrofit.interceptor.RequestEncryptInterceptor
import znyoo.name.netlibrary.retrofit.interceptor.ResponseDecryptInterceptor
import java.util.concurrent.TimeUnit

/**
 * @ProjectName:    BaseProject
 * @Package:        znyoo.name.netlibrary.retrofit
 * @ClassName:      NetCommon
 * @Author:         dhkyhb
 * @CreateDate:     2020/7/2 7:40 PM
 * @Description:    网络框架下的通用方法
 */

fun initClient() = OkHttpClient.Builder().connectTimeout(2000, TimeUnit.SECONDS)
    .addInterceptor(LogInterceptor())
    .addInterceptor(RequestEncryptInterceptor())
    .addInterceptor(ResponseDecryptInterceptor())
    .build()