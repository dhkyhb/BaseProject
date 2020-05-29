package znyoo.name.baseproject.ui.fragment.login

import znyoo.name.baseproject.R
import znyoo.name.baseproject.ui.base.BaseVmFragment
import znyoo.name.baseproject.viewmodel.FirstViewModel
import znyoo.name.baseproject.viewmodel.LoginViewModel

class LoginFragment : BaseVmFragment<LoginViewModel>() {

    override fun initData() {
    }

    override fun initOnClick() {
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
