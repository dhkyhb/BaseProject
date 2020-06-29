package znyoo.name.baseproject.repository

import com.blankj.utilcode.util.EncodeUtils
import com.blankj.utilcode.util.EncryptUtils
import com.blankj.utilcode.util.LogUtils
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import okhttp3.MediaType
import okhttp3.RequestBody
import znyoo.name.base.base.BaseReponse
import znyoo.name.base.base.LoginBean
import znyoo.name.base.base.loginParamster
import znyoo.name.baseproject.api.NetServices
import znyoo.name.netlibrary.retrofit.api.A.STAFFLOGINBYSMS
import javax.inject.Inject

/**
 * @ProjectName:    BaseProject
 * @Package:        znyoo.name.baseproject.repository
 * @ClassName:      MainRepostory
 * @Author:         dhkyhb
 * @CreateDate:     2020/6/10 10:53 AM
 * @Description:    主页的请求仓库类
 */
class LoginRepostory @Inject constructor(private val netServices: NetServices) : BaseRepository() {

    suspend fun login(loginParamster: loginParamster): BaseReponse<LoginBean> {
        val obj = createCommonParams()
        obj.addProperty("method", STAFFLOGINBYSMS)
        obj.addProperty("cellPhone", loginParamster.cellPhone)
        obj.addProperty("smsCode", loginParamster.smsCode)
        obj.addProperty("uuid", loginParamster.uuid)
        return apiCall {netServices.FuncLogin(getRequestBody(paramsMethod(obj)))}
    }

    fun createCommonParams(): JsonObject{
        val obj = JsonObject()
        obj.addProperty("txnDate", "1")
        obj.addProperty("apiVersion", "1")
        obj.addProperty("apiVersion", "M")
        obj.addProperty("apiVersion", "generalVersion")
        return obj
    }

    fun paramsMethod(obj: JsonObject): String {
        val content = JsonObject()
        var contentStr = obj.toString()
        LogUtils.e("contentStr==========", contentStr)
        val signStrs = contentStr;
        val sign = EncryptUtils.encryptSHA256ToString(signStrs)
        content.addProperty("content", contentStr)
        content.addProperty("sign", sign)
        return content.toString()
    }

    fun getRequestBody(content: String): RequestBody {
        //json请求
        val JSON = MediaType
            .parse("application/json; charset=utf-8")
        return RequestBody.create(JSON, content)
    }

}