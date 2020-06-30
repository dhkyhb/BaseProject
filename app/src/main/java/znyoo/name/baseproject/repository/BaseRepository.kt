package znyoo.name.baseproject.repository

import com.blankj.utilcode.util.LogUtils
import znyoo.name.base.base.BaseReponse

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
        shouldFetch: Boolean = true,//是否应该从网络获取
        saveCallResult: suspend (db: BaseReponse<T>) -> Unit = {},
        loadFromDb: suspend () -> BaseReponse<T> = { BaseReponse(data = null) },
        call: suspend () -> BaseReponse<T>
    ): BaseReponse<T> {
        var reponse: BaseReponse<T>
        if (shouldFetch) {
            LogUtils.i("load data from network")
            reponse = call.invoke()
            LogUtils.i("save db -------- $reponse")
            saveCallResult(reponse)
        }else {
            reponse = loadFromDb()
            LogUtils.i("load data from db -------- $reponse")
        }
        return reponse
    }

}