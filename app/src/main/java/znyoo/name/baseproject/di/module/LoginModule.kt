package znyoo.name.baseproject.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import znyoo.name.baseproject.MainActivity
import znyoo.name.baseproject.di.scope.ActivityScope
import znyoo.name.baseproject.ui.activity.LoginActivity

@Module()
abstract class LoginModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [LoginFragmntModule::class])
    abstract fun contributeLoginActivity(): LoginActivity

}