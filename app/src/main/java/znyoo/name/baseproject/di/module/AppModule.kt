package znyoo.name.baseproject.di.module

import com.android.example.github.util.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import znyoo.name.base.common.ISTEST
import znyoo.name.baseproject.api.NetServices
import znyoo.name.netlibrary.retrofit.api.A
import javax.inject.Singleton

/**
 * @ProjectName:    BaseProject
 * @Package:        znyoo.name.baseproject.di.module
 * @ClassName:      AppModule
 * @Author:         dhkyhb
 * @CreateDate:     2020/6/8 10:34 PM
 * @Description:    java类作用描述
 */
@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun providerNetServices(): NetServices {
        val url = if (ISTEST)  A.TEST_MAIN_URL
        else A.MAIN_URL

        return Retrofit.Builder()
            .baseUrl(url)
//            .addConverterFactory(MoshiConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(NetServices::class.java)
    }

}