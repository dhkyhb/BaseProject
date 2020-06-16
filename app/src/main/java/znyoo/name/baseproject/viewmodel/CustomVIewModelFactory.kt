package znyoo.name.baseproject.viewmodel

import android.app.Application
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import znyoo.name.baseproject.SampleApplication
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Provider

/**
 * @ProjectName:    BaseProject
 * @Package:        znyoo.name.baseproject.viewmodel
 * @ClassName:      CustomVIewModelFactory
 * @Author:         dhkyhb
 * @CreateDate:     2020/6/10 6:00 PM
 * @Description:    viewmodelfactory 用于创建含有SavedStateHandle的自定义属性viewmodel工厂类
 */
class CustomVIewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
            modelClass.isAssignableFrom(modelClass)
        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
        return creator.get() as T
    }
}