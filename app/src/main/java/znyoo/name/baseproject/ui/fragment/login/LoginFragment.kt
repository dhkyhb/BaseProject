package znyoo.name.baseproject.ui.fragment.login

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.qmuiteam.qmui.kotlin.onDebounceClick
import kotlinx.android.synthetic.main.fragment_login.*
import znyoo.name.baseproject.R
import znyoo.name.baseproject.ui.base.BaseVmFragment
import znyoo.name.baseproject.viewmodel.MainViewModel
import znyoo.name.baseproject.viewmodel.LoginViewModel
import javax.inject.Inject

class LoginFragment : BaseVmFragment<LoginViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val loginViewModel by viewModels<LoginViewModel>{
        viewModelFactory
    }

    override fun initData() {
    }

    override fun initOnClick() {
        login.onDebounceClick {

        }
    }


    override fun startObserve() {

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
