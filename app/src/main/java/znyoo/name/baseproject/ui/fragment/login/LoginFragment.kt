package znyoo.name.baseproject.ui.fragment.login

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.qmuiteam.qmui.kotlin.onDebounceClick
import kotlinx.android.synthetic.main.fragment_login.*
import znyoo.name.base.base.BaseReponse
import znyoo.name.base.base.LoginBean
import znyoo.name.base.base.loginParamster
import znyoo.name.baseproject.R
import znyoo.name.baseproject.di.Injectable
import znyoo.name.baseproject.ui.base.BaseVmFragment
import znyoo.name.baseproject.viewmodel.MainViewModel
import znyoo.name.baseproject.viewmodel.LoginViewModel
import javax.inject.Inject

class LoginFragment : BaseVmFragment<LoginViewModel>(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val loginViewModel : LoginViewModel by viewModels{
        viewModelFactory
    }

    override fun initData() {
    }

    override fun initOnClick() {
        login.onDebounceClick {
            loginViewModel.mloginParamster.value = loginParamster("13856474956", "324f", "34562345")
        }
    }


    override fun startObserve() {
        loginViewModel.loginResult.observe(this,
            Observer {
                ToastUtils.showShort("login success")
            })
    }

    override fun handleError() {

    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_login
    }

    override fun providerVMClass(): Class<LoginViewModel>? {
        return LoginViewModel::class.java
    }
}
