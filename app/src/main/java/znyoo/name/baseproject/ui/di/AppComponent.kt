package znyoo.name.baseproject.ui.di

import dagger.Component

/**
 *  created by dhkyhb
 *  date: 2020/6/2 17:06
 *  description:
 *  link:
 */

@Component(modules = [
])
interface AppComponent{

    @Component.Factory
    interface Factory{
        fun create(): AppComponent
    }

    

}