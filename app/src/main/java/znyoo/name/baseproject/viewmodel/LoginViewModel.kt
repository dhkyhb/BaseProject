package znyoo.name.baseproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.LogUtils
import znyoo.name.base.base.BaseReponse
import znyoo.name.base.base.BaseViewModel
import znyoo.name.base.base.LoginBean
import znyoo.name.base.base.loginParamster
import znyoo.name.baseproject.repository.LoginRepostory
import javax.inject.Inject

/**
 *  created by dhkyhb
 *  date: 2020/5/15 16:27
 *  description:  viewmodel通用设置类
 *  link:
 */
class LoginViewModel @Inject constructor(val api: LoginRepostory) : BaseViewModel() {

    private val mloginParamster = MutableLiveData<loginParamster>()

    private var _loginResult = MediatorLiveData<BaseReponse<LoginBean>>()
    val loginResult: LiveData<BaseReponse<LoginBean>>
        get() = _loginResult

    init {
        _loginResult.addSource(mloginParamster) {
            getLoginResult(it)
        }
    }

    private fun getLoginResult(loginParamster: loginParamster) {
        handleResult {
            val response = api.login(loginParamster)
            _loginResult.postValue(response)
        }
    }

    fun retry() {
        val paramster = mloginParamster.value
        mloginParamster.postValue(paramster)
    }

    fun setLoginParamster(paramster: loginParamster) {
        if (mloginParamster.value == paramster) {
            LogUtils.i("same content")
            return
        }
        mloginParamster.postValue(paramster)
    }

}