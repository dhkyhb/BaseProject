package znyoo.name.baseproject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDex;

import com.blankj.utilcode.util.LogUtils;
import com.coder.zzq.smartshow.core.SmartShow;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.interfaces.BetaPatchListener;
import com.tencent.tinker.entry.DefaultApplicationLike;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import me.jessyan.autosize.AutoSizeConfig;
import znyoo.name.base.base.BaseActivity;
import znyoo.name.base.common.CommonSetKt;
import znyoo.name.baseproject.ui.di.AppInjector;
import znyoo.name.netlibrary.retrofit.OnlineConfig;
import znyoo.name.netlibrary.retrofit.XH_RXOnline;

import static znyoo.name.base.common.CommonSetKt.BUGLY_APPID;
import static znyoo.name.base.common.CommonSetKt.ISTEST;
import static znyoo.name.base.common.UrlConstantKt.MAIN_URL;
import static znyoo.name.base.common.UrlConstantKt.TEST_MAIN_URL;


/**
 * 自定义ApplicationLike类.
 *
 * 注意：这个类是Application的代理类，以前所有在Application的实现必须要全部拷贝到这里<br/>
 *
 *  make your Application implement HasAndroidInjector and @Inject a DispatchingAndroidInjector<Object> to return from the androidInjector() method:
 * @author wenjiewu
 * @since 2016/11/7
 */
public class SampleApplicationLike extends DefaultApplicationLike implements HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    public static final String TAG = "Tinker.SampleApplicationLike";

    private static SampleApplicationLike instance;

    private List<Activity> activitys;

    //static 代码段可以防止内存泄露
    static {

        //设置全局的Header构建器  recycleview下拉上拉样式
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                //                layout.setPrimaryColorsId(R.color.blackColor, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Scale);//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });
    }

    public SampleApplicationLike(Application application, int tinkerFlags,
                                 boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime,
                                 long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime,
                applicationStartMillisTime, tinkerResultIntent);
        instance = this;
        activitys = new ArrayList<>();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        initialize();

//        AppInjector.INSTANCE.inject(getApplication());
    }


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);

        // TODO: 安装tinker
        Beta.installTinker(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Beta.unInit();
    }

    /**
     * 单例，返回一个实例
     */
    public static SampleApplicationLike getInstance() {
        return instance;
    }


    // 遍历所有Activity并finish
    public void addActivity(Activity activity) {
        if (activitys == null) {
            return;
        }
        if (activitys.size() > 0) {
            if (!activitys.contains(activity)) {
                activitys.add(activity);
            }
        } else {
            activitys.add(activity);
        }
    }

    // 遍历所有Activity并finish
    public void exit() {
        if (activitys != null && activitys.size() > 0) {
            for (Activity activity : activitys) {
                activity.finish();
            }
        }
        System.exit(0);
    }

    private void initialize(){
        Beta.initDelay = 0;
        Beta.upgradeCheckPeriod = 0;
        Beta.canShowUpgradeActs.add(BaseActivity.class);
        // 设置是否开启热更新能力，默认为true
        Beta.enableHotfix = true;
        // 设置是否自动下载补丁，默认为true
        Beta.canAutoDownloadPatch = true;
        // 设置是否自动合成补丁，默认为true
        Beta.canAutoPatch = true;
        // 设置是否提示用户重启，默认为false
        Beta.canNotifyUserRestart = true;
        // 补丁回调接口
        Beta.betaPatchListener = new BetaPatchListener() {
            @Override
            public void onPatchReceived(String patchFile) {
                LogUtils.e("补丁下载地址" + patchFile);
            }

            @Override
            public void onDownloadReceived(long savedLength, long totalLength) {
                LogUtils.e(String.format(Locale.getDefault(), "%s %d%%",
                        Beta.strNotificationDownloading,
                        (int) (totalLength == 0 ? 0 : savedLength * 100 / totalLength)));
            }

            @Override
            public void onDownloadSuccess(String msg) {
                LogUtils.e("补丁下载成功");
            }

            @Override
            public void onDownloadFailure(String msg) {
                LogUtils.e("补丁下载失败");
            }

            @Override
            public void onApplySuccess(String msg) {
                LogUtils.e("补丁应用成功");
            }

            @Override
            public void onApplyFailure(String msg) {
                LogUtils.e("补丁应用失败");
            }

            @Override
            public void onPatchRollback() {

            }
        };
        // 设置开发设备，默认为false，上传补丁如果下发范围指定为“开发设备”，需要调用此接口来标识开发设备
        Bugly.setIsDevelopmentDevice(getApplication(), false);
        // 多渠道需求塞入
        // String channel = WalleChannelReader.getChannel(getApplication());
        // Bugly.setAppChannel(getApplication(), channel);
        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        Bugly.init(getApplication(), BUGLY_APPID, false);
        Beta.checkUpgrade(false,false);


        //rutosize
//        AutoSizeConfig.getInstance().setCustomFragment(true);
        SmartShow.init(getApplication());

        initOnline();
    }

    public void initOnline() {
        OnlineConfig config = new OnlineConfig();
        config.url = CommonSetKt.ISTEST? TEST_MAIN_URL : MAIN_URL;
        config.isShowWait = false;
        XH_RXOnline.getInstance().setOnlineConfig(config);
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }
}
