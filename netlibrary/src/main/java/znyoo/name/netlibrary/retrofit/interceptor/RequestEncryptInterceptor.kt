package znyoo.name.netlibrary.retrofit.interceptor

import com.blankj.utilcode.util.LogUtils
import com.google.gson.JsonParser
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.Response
import okio.Buffer
import org.json.JSONObject
import java.net.URLDecoder
import java.nio.charset.Charset

/**
 * @ProjectName:    BaseProject
 * @Package:        znyoo.name.netlibrary.retrofit.interceptor
 * @ClassName:      RequestEncryptInterceptor
 * @Author:         dhkyhb
 * @CreateDate:     2020/7/2 7:39 PM
 * @Description:     java类作用描述
 */
class RequestEncryptInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val JSON = MediaType
            .parse("application/json; charset=utf-8")
        var charset = Charset.forName("UTF-8")
        var request = chain.request()
        val method = request.method().toLowerCase().trim { it <= ' ' }
        try {
            val requestBody = request.body()
            charset = JSON!!.charset(charset)
            val buffer = Buffer()
            requestBody!!.writeTo(buffer)
            val requestData =
                URLDecoder.decode(buffer.readString(charset).trim { it <= ' ' }, "utf-8")
            val obj = JSONObject(requestData)
            val mName = obj.getString("method")

            //加解密
//                if (UrlConstantKt.ADDORDER.equals(mName)
//                        || UrlConstantKt.GETORDER.equals(mName)
//                        || UrlConstantKt.UPDATEORDERSTATUS.equals(mName)
//                ) {
//                    KEY = (String) SPUtil.get(App.Companion.getInstace(), "key", CommonSets.KEY);
//                } else {
//            KEY = KEY;
//                }
            /*构建新的请求体*/
//            val newRequestBody = RequestBody.create(
//                JSON,
//                paramsMethod(JsonParser().parse(requestData).getAsJsonObject())
//            )
            /*构建新的requestBuilder*/
            val newRequestBuilder = request.newBuilder()
            when (method) {
                "post" -> newRequestBuilder.post(requestBody)
                "put" -> newRequestBuilder.put(requestBody)
            }
            request = newRequestBuilder.build()
        } catch (e: Exception) {
            LogUtils.e("加密失败!!!")
            chain.proceed(request)
        }
        return chain.proceed(request)
    }
}