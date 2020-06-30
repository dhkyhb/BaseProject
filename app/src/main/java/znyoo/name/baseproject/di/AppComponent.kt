package znyoo.name.baseproject.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import znyoo.name.baseproject.SampleApplication
import znyoo.name.baseproject.di.module.AcitivtyModule
import znyoo.name.baseproject.di.module.AppModule
import znyoo.name.baseproject.di.module.LoginModule
import javax.inject.Singleton

/**
 *  created by dhkyhb
 *  date: 2020/6/2 17:06
 *  description:
 *  link:
 */

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, AcitivtyModule::class])
interface AppComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {

        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance application: SampleApplication): AppComponent
    }

    fun inject(application: SampleApplication)

}