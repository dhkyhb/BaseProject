package znyoo.name.baseproject.ui.fragment.main

import znyoo.name.baseproject.R
import znyoo.name.baseproject.ui.base.BaseVmFragment
import znyoo.name.baseproject.viewmodel.FirstViewModel

class TwoFragment : BaseVmFragment<FirstViewModel>() {

    override fun initData() {
    }

    override fun initOnClick() {
    }


    override fun startObserve() {

    }

    override fun handleError() {

    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_two
    }

    override fun providerVMClass(): Class<FirstViewModel>? {
        return FirstViewModel::class.java
    }
}
