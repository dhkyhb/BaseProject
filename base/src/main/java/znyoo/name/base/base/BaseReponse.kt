package znyoo.name.base.base

import com.squareup.moshi.Json

/**
 *  created by dhkyhb
 *  date: 2020/5/12 20:38
 *  description:  基础返回类
 *  link:
 */

data class BaseReponse<T>(
    @Json(name = "code")
    val code: String,
    @Json(name = "data")
    val data: T,
    @Json(name = "message")
    val message: String
)

