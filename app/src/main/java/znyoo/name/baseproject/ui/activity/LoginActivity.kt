package znyoo.name.baseproject.ui.activity

import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import znyoo.name.baseproject.R
import znyoo.name.baseproject.di.Injectable
import znyoo.name.baseproject.ui.base.BaseVmActivity
import znyoo.name.baseproject.viewmodel.LoginViewModel
import javax.inject.Inject

class LoginActivity : BaseVmActivity(), Injectable {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>


    override fun initOnClick() {

    }

    override fun initData() {

    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    override fun getLayoutId(): Int = R.layout.activity_login
}
