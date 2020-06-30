package znyoo.name.baseproject.ui.fragment.main

import com.blankj.utilcode.util.LogUtils
import kotlinx.android.synthetic.main.fragment_first.*
import znyoo.name.base.extension.LoginClick
import znyoo.name.base.extension.click
import znyoo.name.baseproject.R
import znyoo.name.baseproject.di.Injectable
import znyoo.name.baseproject.ext.loginClick
import znyoo.name.baseproject.ui.base.BaseVmActivity
import znyoo.name.baseproject.ui.base.BaseVmFragment
import znyoo.name.baseproject.viewmodel.MainViewModel

class FirstFragment : BaseVmFragment(), Injectable {

    override fun initData() {
    }

    override fun initOnClick() {
        template.loginClick {
            LogUtils.i("is login")
        }
    }


    override fun startObserve() {

    }

    override fun handleError() {

    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_first
    }

}
