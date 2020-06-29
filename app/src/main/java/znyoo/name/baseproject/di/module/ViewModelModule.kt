package znyoo.name.baseproject.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import znyoo.name.baseproject.di.ViewModelKey
import znyoo.name.baseproject.viewmodel.CustomVIewModelFactory
import znyoo.name.baseproject.viewmodel.LoginViewModel
import znyoo.name.baseproject.viewmodel.MainViewModel

/**
 * @ProjectName:    BaseProject
 * @Package:        znyoo.name.baseproject.di.module
 * @ClassName:      ViewModelModule
 * @Author:         dhkyhb
 * @CreateDate:     2020/6/10 6:07 PM
 * @Description:     java类作用描述
 */
@Module
abstract class ViewModelModule {

    /**
     * @return 必须是viewmodel 不然的话 dagger2编译不过
     */
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: CustomVIewModelFactory): ViewModelProvider.Factory

}