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

    suspend fun <T : Any?> apiCall(call: suspend () -> BaseReponse<T>) = call.invoke()

}