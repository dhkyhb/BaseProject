package znyoo.name.netlibrary.retrofit.interceptor

import com.blankj.utilcode.util.LogUtils
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import java.nio.charset.Charset

/**
 * @ProjectName:    BaseProject
 * @Package:        znyoo.name.netlibrary.retrofit.interceptor
 * @ClassName:      ResponseDecryptInterceptor
 * @Author:         dhkyhb
 * @CreateDate:     2020/7/2 7:39 PM
 * @Description:     java类作用描述
 */
class ResponseDecryptInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var proceed = chain.proceed(request)
        if (proceed.isSuccessful) {
            try {
                val body = proceed.body()
                val source = body!!.source()
                source.request(Long.MAX_VALUE)
                val buffer = source.buffer()
                var charset = Charset.forName("UTF-8")
                val mediaType = body.contentType()
                if (mediaType != null) {
                    charset = mediaType.charset(charset)
                }
                //获取返回数据
                var data = buffer.clone().readString(charset)
                LogUtils.e("Response: $data")

                //获取加密result
//                val result = commonPaseParams(data);
                //获取sign
//                val sign = commonPaseSign(data);
                //解密
//                data = UseRSAUtil.decryptData(result);
                LogUtils.e("Decrypt: $data")
                val responseBody =
                    ResponseBody.create(mediaType, data.trim { it <= ' ' })
                proceed = proceed.newBuilder().body(responseBody).build()
            } catch (e: Exception) {
                LogUtils.e("解密失败!!!")
                return proceed
            }
        }
        return proceed
    }
}