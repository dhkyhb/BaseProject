package znyoo.name.baseproject.di.module

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import znyoo.name.baseproject.api.NetServices
import javax.inject.Singleton

/**
 * @ProjectName:    BaseProject
 * @Package:        znyoo.name.baseproject.di.module
 * @ClassName:      AppModule
 * @Author:         dhkyhb
 * @CreateDate:     2020/6/8 10:34 PM
 * @Description:     java类作用描述
 */
@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun providerNetServices(): NetServices {
        return Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(NetServices::class.java)
    }

}