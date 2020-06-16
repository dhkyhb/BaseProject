package znyoo.name.baseproject.api

import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.http.POST
import znyoo.name.baseproject.api.ApiResponse

/**
 * @ProjectName:    BaseProject
 * @Package:        znyoo.name.baseproject.repository
 * @ClassName:      NetServices
 * @Author:         dhkyhb
 * @CreateDate:     2020/6/8 10:10 PM
 * @Description:    网络请求集合类
 */
interface NetServices {

    @POST("templates")
    fun templateFunc(): LiveData<ApiResponse<String>>

}