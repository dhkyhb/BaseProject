package znyoo.name.baseproject;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import znyoo.name.baseproject.di.AppInjector;

/**
 * 自定义Application.
 *
 * 注意：这个类集成TinkerApplication类，这里面不做任何操作，所有Application的代码都会放到ApplicationLike继承类当中<br/>
 * <pre>
 * 参数解析：
 * 参数1：int tinkerFlags 表示Tinker支持的类型 dex only、library only or all suuport，default: TINKER_ENABLE_ALL
 * 参数2：String delegateClassName Application代理类 这里填写你自定义的ApplicationLike
 * 参数3：String loaderClassName  Tinker的加载器，使用默认即可
 * 参数4：boolean tinkerLoadVerifyFlag  加载dex或者lib是否验证md5，默认为false
 * </pre>
 * @author wenjiewu
 * @since 2016/11/15
 */
public class SampleApplication extends TinkerApplication implements HasAndroidInjector {
    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    public SampleApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "znyoo.name.baseproject.SampleApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        AppInjector.INSTANCE.inject(this);
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }
}
