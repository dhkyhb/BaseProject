package znyoo.name.baseproject.ui.fragment.login

import znyoo.name.baseproject.R
import znyoo.name.baseproject.ui.base.BaseVmFragment
import znyoo.name.baseproject.viewmodel.MainViewModel
import znyoo.name.baseproject.viewmodel.LoginViewModel

class RegistFragment : BaseVmFragment<LoginViewModel>() {

    override fun initData() {
    }

    override fun initOnClick() {
    }


    override fun startObserve() {

    }

    override fun handleError() {

    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_regist
    }

    override fun providerVMClass(): Class<LoginViewModel>? {
        return LoginViewModel::class.java
    }
}
