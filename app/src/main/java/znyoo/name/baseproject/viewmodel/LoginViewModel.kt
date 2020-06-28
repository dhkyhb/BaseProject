package znyoo.name.baseproject.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import znyoo.name.base.base.*
import znyoo.name.baseproject.api.NetServices
import znyoo.name.baseproject.repository.LoginRepostory
import javax.inject.Inject

/**
 *  created by dhkyhb
 *  date: 2020/5/15 16:27
 *  description:
 *  link:
 */
class LoginViewModel @Inject constructor(val api: LoginRepostory) : BaseViewModel(){

    val mloginParamster = MutableLiveData<loginParamster>()

    private var _loginResult = MediatorLiveData<BaseReponse<LoginBean>>()
    val loginResult: LiveData<BaseReponse<LoginBean>>
        get() = _loginResult

    init {
        _loginResult.addSource(mloginParamster){
            getLoginResult(it)
        }
    }

    private fun getLoginResult(loginParamster: loginParamster) {
            handleResult {
                _loginResult.value = api.login(viewModelScope).value
            }
    }

}