package znyoo.name.baseproject.repository

import kotlinx.coroutines.CoroutineScope
import znyoo.name.baseproject.api.NetServices
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

    suspend fun login(coroutineScope: CoroutineScope) = apiCall({
            netServices.FuncLogin()
        }, coroutineScope)

}