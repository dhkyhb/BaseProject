package znyoo.name.baseproject

import android.app.Application
import android.content.Intent
import com.blankj.utilcode.util.LogUtils
import com.coder.zzq.smartshow.core.SmartShow
import com.scwang.smart.refresh.footer.BallPulseFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.tencent.bugly.Bugly
import com.tencent.bugly.beta.Beta
import com.tencent.bugly.beta.interfaces.BetaPatchListener
import com.tencent.tinker.entry.DefaultApplicationLike
import me.jessyan.autosize.AutoSizeConfig
import znyoo.name.base.common.BUGLY_APPID
import znyoo.name.base.common.BaseConfig
import znyoo.name.base.common.ISTEST
import znyoo.name.base.common.initLog
import znyoo.name.netlibrary.retrofit.OnlineConfig
import znyoo.name.netlibrary.retrofit.XH_RXOnline
import znyoo.name.netlibrary.retrofit.api.A.MAIN_URL
import znyoo.name.netlibrary.retrofit.api.A.TEST_MAIN_URL


/**
 * @ProjectName:    BaseProject
 * @Package:        znyoo.name.baseproject
 * @ClassName:      SampliApplicationLike
 * @Author:         dhkyhb
 * @CreateDate:     2020/6/24 11:22 AM
 * @Description:     java类作用描述
 */
open class SampleApplicationLike(
    application: Application?, tinkerFlags: Int,
    tinkerLoadVerifyFlag: Boolean, applicationStartElapsedTime: Long,
    applicationStartMillisTime: Long, tinkerResultIntent: Intent?
) : DefaultApplicationLike(
    application, tinkerFlags,
    tinkerLoadVerifyFlag, applicationStartElapsedTime,
    applicationStartMillisTime, tinkerResultIntent
) {

    init {
        instance = this
    }

    //static 代码段可以防止内存泄露
    companion object {
        init {
            //设置全局的Header构建器
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout -> MaterialHeader(context) }
            //设置全局的Footer构建器
            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout -> BallPulseFooter(context) }
        }

        val TAG = "Tinker.SampleApplicationLike"
        private lateinit var instance: SampleApplicationLike
        fun getInstance(): SampleApplicationLike {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        initialize()
//        inject(application as SampleApplication)
    }

    private fun initialize(){

        Beta.initDelay = 0
        Beta.upgradeCheckPeriod = 0
        Beta.canShowUpgradeActs.add(MainActivity::class.java)
        // 设置是否开启热更新能力，默认为true
        Beta.enableHotfix = true
        // 设置是否自动下载补丁，默认为true
        Beta.canAutoDownloadPatch = true
        // 设置是否自动合成补丁，默认为true
        Beta.canAutoPatch = true
        // 设置是否提示用户重启，默认为false
        Beta.canNotifyUserRestart = true
        // 补丁回调接口
        Beta.betaPatchListener = object : BetaPatchListener {
            override fun onApplySuccess(p0: String?) {
                LogUtils.i("onApplySuccess")
                LogUtils.e("补丁下载地址$p0")

            }

            override fun onPatchReceived(p0: String?) {
                LogUtils.i("onPatchReceived")
            }

            override fun onApplyFailure(p0: String?) {
                LogUtils.i("onApplyFailure")
            }

            override fun onDownloadReceived(p0: Long, p1: Long) {
                LogUtils.i("onDownloadReceived")
            }

            override fun onDownloadSuccess(p0: String?) {
                LogUtils.i("onDownloadSuccess")
            }

            override fun onDownloadFailure(p0: String?) {
                LogUtils.i("onDownloadFailure")
            }

            override fun onPatchRollback() {
                LogUtils.i("onPatchRollback")
            }
        }

        // 设置开发设备，默认为false，上传补丁如果下发范围指定为“开发设备”，需要调用此接口来标识开发设备
        Bugly.setIsDevelopmentDevice(application, false)
        // 多渠道需求塞入
        // String channel = WalleChannelReader.getChannel(getApplication());
        // Bugly.setAppChannel(getApplication(), channel);
        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        Bugly.init(application, BUGLY_APPID, false)
        Beta.checkUpgrade(false, false)

        //rutosize 开启适配fragment
        AutoSizeConfig.getInstance().setCustomFragment(true);
        SmartShow.init(application)

        BaseConfig().init(application)
        initOnline()
        initLog()
    }

    open fun initOnline() {
        val config = OnlineConfig().apply {
            url = if (ISTEST) TEST_MAIN_URL else MAIN_URL
            isShowWait = false
        }
        XH_RXOnline.getInstance().setOnlineConfig(config)
    }


}