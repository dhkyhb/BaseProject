package znyoo.name.base.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.NetworkUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import znyoo.name.base.R
import znyoo.name.base.common.BaseConfig

/**
 *  created by dhkyhb
 *  date: 2020/5/9 16:47
 *  description: 1、viewmodel基础类，包含网络请求error时的状况。
 *               2、将数据逻辑放在 ViewModel 类中。 ViewModel 应充当界面控制器与应用其余部分之间的连接器。不过要注意，ViewModel 不负责获取数据（例如，从网络获取）。ViewModel 应调用相应的组件来获取数据，然后将结果提供给界面控制器。
 *               3、避免在 ViewModel 中引用 View 或 Activity 上下文。 如果 ViewModel 存在的时间比 Activity 更长（在配置更改的情况下），Activity 将泄露并且不会由垃圾回收器妥善处置。
 *  @param saveStateHandle 主要用于数据恢复、viewmodel生命周期长于Activity且能在配置更改之后数据恢复，但是因为资源限制导致的的Activity销毁之后的数据恢复只能通过
 *         onSaveInstanceStated来恢复数据、但这种方法只能恢复少量数据，大量数据会阻塞UI线程、此时可以通过saveStateHandle来完成这种情况下的数据恢复.
 */
abstract class BaseViewModel() : ViewModel() {

    private lateinit var saveStateHandle: SavedStateHandle

    constructor(saveStateHandle: SavedStateHandle) : this() {
        this.saveStateHandle = saveStateHandle
    }

    val mException: MutableLiveData<Exception> = MutableLiveData()

    val error = MutableLiveData<String>()

    /**
     * 统一处理 请求结果
     */
    protected fun handleResult(func: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            LogUtils.i("handleResult start")
            try {
                val response = func()
                LogUtils.e("response: $response")
            } catch (e: Exception) {//协程抛出异常
                LogUtils.e("Coroutine error $e -------- ${e.message}")
                mException.postValue(e)
                if (NetworkUtils.isAvailableByPing()) { //网络正常
                    error.postValue(e.message ?: BaseConfig().context.getString(R.string.unknown_error))
                } else {
                    error.postValue(BaseConfig().context.getString(R.string.net_error))
                }
            }
        }
    }

    /**
     * 统一处理返回Response方法类
     */
    suspend fun <T>executeResponse(reponse: ApiResponse<T>) {

    }

    /**
     * 案例
     * 需要在资源限制导致重建的场景下保存的数据
     * 用LiveData暴露，不能让外部直接通过LiveData去修改内部的值
     */
    private val mSavedTemplates: MutableLiveData<String>? =
        saveStateHandle.getLiveData(TEMPLATE_KEY)

    /**
     * 案例
     * 设置值
     */
    private fun setTemplate(template: String) {
        saveStateHandle.set(template, template)
    }

    fun setSaveStateHandle(saveStateHandle: SavedStateHandle) {
        this.saveStateHandle = saveStateHandle
    }

    companion object {
        const val TEMPLATE_KEY = "template_key"
    }
}