package znyoo.name.baseproject.ui.fragment.login

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.ToastUtils
import com.qmuiteam.qmui.kotlin.onClick
import com.qmuiteam.qmui.kotlin.onDebounceClick
import kotlinx.android.synthetic.main.fragment_login.*
import znyoo.name.base.base.loginParamster
import znyoo.name.baseproject.R
import znyoo.name.baseproject.di.Injectable
import znyoo.name.baseproject.ext.onClick
import znyoo.name.baseproject.ui.base.BaseVmFragment
import znyoo.name.baseproject.viewmodel.LoginViewModel
import javax.inject.Inject

class LoginFragment : BaseVmFragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val loginViewModel: LoginViewModel by viewModels {
        viewModelFactory
    }

    override fun initData() {
    }

    override fun initOnClick() {
        var f = true
        login.onClick {
            showLoading()
            if (f) {
                loginViewModel.setLoginParamster(loginParamster("13856474956", "324f", "34562345"))
                f = false
            } else {
                loginViewModel.retry()
            }
        }
    }


    override fun startObserve() {
        loginViewModel.loginResult.observe(this,
            Observer {
                ToastUtils.showShort("login success")
            })
    }

    override fun handleError() {
        loginViewModel.error.observe(this,
            Observer<String> {
                ToastUtils.showLong(it)
            })
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_login
    }

}
