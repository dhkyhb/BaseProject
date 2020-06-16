package znyoo.name.baseproject.ui.fragment.main

import znyoo.name.baseproject.R
import znyoo.name.baseproject.di.Injectable
import znyoo.name.baseproject.ui.base.BaseVmFragment
import znyoo.name.baseproject.viewmodel.MainViewModel

class FirstFragment : BaseVmFragment<MainViewModel>(), Injectable {

    override fun initData() {
    }

    override fun initOnClick() {
    }


    override fun startObserve() {

    }

    override fun handleError() {

    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_first
    }

    override fun providerVMClass(): Class<MainViewModel>? {
        return MainViewModel::class.java
    }
}
