package znyoo.name.base.base

import com.squareup.moshi.Json

/**
 *  created by dhkyhb
 *  date: 2020/5/12 20:38
 *  description:  基础返回类
 *  link:
 */

data class BaseReponse<T>(
    val code: String = "",
    val data: T?,
    val message: String = ""
)

data class loginParamster(
    @Json(name = "cellPhone")
    var cellPhone: String,
    @Json(name = "smsCode")
    var smsCode: String,
    @Json(name = "uuid")
    var uuid: String
)

data class LoginBean(
    val staffMid: String,
    val userName: String,
    val deviceCount: Int,
    val srcAmt: Double,
    val exceptionList: List<LoginData>,
    val exceptionCount: Int
)

data class LoginData(val terminalName: String)

