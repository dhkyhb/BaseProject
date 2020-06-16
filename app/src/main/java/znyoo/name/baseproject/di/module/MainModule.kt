package znyoo.name.baseproject.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import znyoo.name.baseproject.MainActivity
import znyoo.name.baseproject.di.scope.ActivityScope

@Module()
abstract class MainModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainFragmntModule::class])
    abstract fun contributeMainActivity(): MainActivity

}