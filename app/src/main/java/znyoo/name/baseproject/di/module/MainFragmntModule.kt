package znyoo.name.baseproject.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import znyoo.name.baseproject.MainActivity
import znyoo.name.baseproject.di.scope.ActivityScope
import znyoo.name.baseproject.ui.fragment.main.FirstFragment
import znyoo.name.baseproject.ui.fragment.main.FourFragment
import znyoo.name.baseproject.ui.fragment.main.ThreeFragment
import znyoo.name.baseproject.ui.fragment.main.TwoFragment

/**
 * @ProjectName:    BaseProject
 * @Package:        znyoo.name.baseproject.di.module
 * @ClassName:      MainFragmntSubComponent
 * @Author:         dhkyhb
 * @CreateDate:     2020/6/10 5:21 PM
 * @Description:     java类作用描述
 */
@Module
abstract class MainFragmntModule {

    @ContributesAndroidInjector
    abstract fun contributeFragmentOne():FirstFragment

    @ContributesAndroidInjector
    abstract fun contributeFragmentTwo():TwoFragment

    @ContributesAndroidInjector
    abstract fun contributeFragmentThree():ThreeFragment

    @ContributesAndroidInjector
    abstract fun contributeFragmentFour():FourFragment

}