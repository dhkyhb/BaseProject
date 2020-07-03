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
        return apiCall {netServices.FuncLogin()}
    }

}