package znyoo.name.baseproject.api

import androidx.lifecycle.LiveData
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST
import znyoo.name.base.base.LoginBean
import znyoo.name.base.base.ApiResponse
import znyoo.name.base.base.BaseReponse
import znyoo.name.netlibrary.retrofit.api.A

/**
 * @ProjectName:    BaseProject
 * @Package:        znyoo.name.baseproject.repository
 * @ClassName:      NetServices
 * @Author:         dhkyhb
 * @CreateDate:     2020/6/8 10:10 PM
 * @Description:    网络请求集合类
 */
interface NetServices {

    @POST(A.TEMPLATES)
    suspend fun templateFunc(): BaseReponse<String>

    @POST(A.STAFFLOGINBYSMS)
    suspend fun FuncLogin(@Body body: RequestBody): BaseReponse<LoginBean>

}