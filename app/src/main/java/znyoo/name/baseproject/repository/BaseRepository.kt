package znyoo.name.baseproject.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import znyoo.name.base.base.*

/**
 * @ProjectName:    BaseProject
 * @Package:        znyoo.name.baseproject.repository
 * @ClassName:      BaseRepository
 * @Author:         dhkyhb
 * @CreateDate:     2020/6/28 9:56 AM
 * @Description:     java类作用描述
 */
open class BaseRepository {

    suspend fun <T : Any?> apiCall(
        call: suspend () -> LiveData<ApiResponse<BaseReponse<T>>>, //接口请求 获取结果
        //协程运行域
        coroutineScope: CoroutineScope,
        //保存数据到数据库中
        saveDb: suspend (dbBean: BaseReponse<T>) -> Unit = {},
        emptyServices: () -> Unit = {},
        errorServices: (error: String) -> Unit = {}
    ): LiveData<BaseReponse<T>> {
        val result = MediatorLiveData<BaseReponse<T>>()
        val reponse = call.invoke()

        result.addSource(reponse) {
            coroutineScope.launch {
                when (reponse.value) {
                    is ApiSuccessResponse -> {
                        val apiSuccessResponse = reponse.value as ApiSuccessResponse
                        saveDb(apiSuccessResponse.body)
                        result.postValue(apiSuccessResponse.body)
                    }
                    is ApiEmptyResponse -> {
                        emptyServices()
                    }
                    is APiFailureResponse -> {
                        val apiFailureResponse = reponse.value as APiFailureResponse
                        errorServices(apiFailureResponse.errorMessage)
                    }
                }
            }
        }
//        reponse.value.
        return result
    }

}