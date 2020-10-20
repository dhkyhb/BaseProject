package znyoo.name.baseproject

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Environment
import android.util.Log
import android.view.View
import com.blankj.utilcode.util.LogUtils
import com.coder.zzq.smartshow.core.SmartShow
import com.scwang.smart.refresh.footer.BallPulseFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.tencent.bugly.Bugly
import com.tencent.bugly.beta.Beta
import com.tencent.bugly.beta.UpgradeInfo
import com.tencent.bugly.beta.download.DownloadListener
import com.tencent.bugly.beta.download.DownloadTask
import com.tencent.bugly.beta.ui.UILifecycleListener
import com.tencent.bugly.beta.upgrade.UpgradeListener
import com.tencent.bugly.beta.upgrade.UpgradeStateListener
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

        /**** Beta高级设置*****/
        /**
         * true表示app启动自动初始化升级模块；
         * false不好自动初始化
         * 开发者如果担心sdk初始化影响app启动速度，可以设置为false
         * 在后面某个时刻手动调用
         */
        Beta.autoInit = true

        /**
         * true表示初始化时自动检查升级
         * false表示不会自动检查升级，需要手动调用Beta.checkUpgrade()方法
         */
        Beta.autoCheckUpgrade = true

        /**
         * 设置升级周期为60s（默认检查周期为0s），60s内SDK不重复向后天请求策略
         */
        Beta.initDelay = 1 * 1000.toLong()

        /**
         * 设置通知栏大图标，largeIconId为项目中的图片资源；
         */
        Beta.largeIconId = R.mipmap.ic_launcher

        /**
         * 设置状态栏小图标，smallIconId为项目中的图片资源id;
         */
        Beta.smallIconId = R.mipmap.ic_launcher


        /**
         * 设置更新弹窗默认展示的banner，defaultBannerId为项目中的图片资源Id;
         * 当后台配置的banner拉取失败时显示此banner，默认不设置则展示“loading“;
         */
        Beta.defaultBannerId = R.mipmap.ic_launcher

        /**
         * 设置sd卡的Download为更新资源保存目录;
         * 后续更新资源会保存在此目录，需要在manifest中添加WRITE_EXTERNAL_STORAGE权限;
         */
        Beta.storageDir =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

        /**
         * 点击过确认的弹窗在APP下次启动自动检查更新时会再次显示;
         */
        Beta.showInterruptedStrategy = false

        /**
         * 只允许在MainActivity上显示更新弹窗，其他activity上不显示弹窗;
         * 不设置会默认所有activity都可以显示弹窗;
         */
        Beta.canShowUpgradeActs.add(MainActivity::class.java)


        /**
         *  设置自定义升级对话框UI布局
         *  注意：因为要保持接口统一，需要用户在指定控件按照以下方式设置tag，否则会影响您的正常使用：
         *  标题：beta_title，如：android:tag="beta_title"
         *  升级信息：beta_upgrade_info  如： android:tag="beta_upgrade_info"
         *  更新属性：beta_upgrade_feature 如： android:tag="beta_upgrade_feature"
         *  取消按钮：beta_cancel_button 如：android:tag="beta_cancel_button"
         *  确定按钮：beta_confirm_button 如：android:tag="beta_confirm_button"
         *  详见layout/upgrade_dialog.xml
         */
//        Beta.upgradeDialogLayoutId = R.layout.upgrade_dialog;

        /**
         * 设置自定义tip弹窗UI布局
         * 注意：因为要保持接口统一，需要用户在指定控件按照以下方式设置tag，否则会影响您的正常使用：
         *  标题：beta_title，如：android:tag="beta_title"
         *  提示信息：beta_tip_message 如： android:tag="beta_tip_message"
         *  取消按钮：beta_cancel_button 如：android:tag="beta_cancel_button"
         *  确定按钮：beta_confirm_button 如：android:tag="beta_confirm_button"
         *  详见layout/tips_dialog.xml
         */
//        Beta.tipsDialogLayoutId = R.layout.tips_dialog;

        /**
         *  如果想监听升级对话框的生命周期事件，可以通过设置OnUILifecycleListener接口
         *  回调参数解释：
         *  context - 当前弹窗上下文对象
         *  view - 升级对话框的根布局视图，可通过这个对象查找指定view控件
         *  upgradeInfo - 升级信息
         */
//        Beta.upgradeDialogLifecycleListener = object: UILifecycleListener<UpgradeInfo>() {
//
//            override fun onCreate(p0: Context?, p1: View?, p2: UpgradeInfo?) {
//                LogUtils.d(TAG, "onCreate")
//            }
//
//            override fun onResume(p0: Context?, p1: View?, p2: UpgradeInfo?) {
//                LogUtils.d(TAG, "onResume")
//            }
//
//            override fun onPause(p0: Context?, p1: View?, p2: UpgradeInfo?) {
//                LogUtils.d(TAG, "onPause")
//            }
//
//            override fun onStart(p0: Context?, p1: View?, p2: UpgradeInfo?) {
//                LogUtils.d(TAG, "onStart")
//            }
//
//            override fun onStop(p0: Context?, p1: View?, p2: UpgradeInfo?) {
//                LogUtils.d(TAG, "onStop")
//            }
//
//            override fun onDestroy(p0: Context?, p1: View?, p2: UpgradeInfo?) {
//                LogUtils.d(TAG, "onDestroy")
//            }
//        };

        /**
         * 自定义Activity参考，通过回调接口来跳转到你自定义的Actiivty中。
         */
//         Beta.upgradeListener = object: UpgradeListener {
//             override fun onUpgrade(p0: Int, p1: UpgradeInfo?, p2: Boolean, p3: Boolean) {
//                 if (p1 != null) {
//                     val i = Intent()
//                     i.setClass(application, MainActivity::class.java)
//                     i.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
//                     application.startActivity(i)
//                 } else {
//
//                 }
//             }
//         }


        //监听安装包下载状态
        Beta.downloadListener =
            object : DownloadListener {
                override fun onReceive(downloadTask: DownloadTask) {
                    LogUtils.d(
                        "downloadListener receive apk file"
                    )
                }

                override fun onCompleted(downloadTask: DownloadTask) {
                    LogUtils.d(
                        "downloadListener download apk file success"
                    )
                }

                override fun onFailed(
                    downloadTask: DownloadTask,
                    i: Int,
                    s: String
                ) {
                    LogUtils.d(
                        "downloadListener download apk file fail"
                    )
                }
            }

        //监听APP升级状态

        //监听APP升级状态
        Beta.upgradeStateListener = object : UpgradeStateListener {
            override fun onUpgradeFailed(b: Boolean) {
                LogUtils.d(
                    "upgradeStateListener upgrade fail"
                )
            }

            override fun onUpgradeSuccess(b: Boolean) {
                LogUtils.d(
                    "upgradeStateListener upgrade success"
                )
            }

            override fun onUpgradeNoVersion(b: Boolean) {
                LogUtils.d(
                    "upgradeStateListener upgrade has no new version"
                )
            }

            override fun onUpgrading(b: Boolean) {
                LogUtils.d(
                    "upgradeStateListener upgrading"
                )
            }

            override fun onDownloadCompleted(b: Boolean) {
                LogUtils.d(
                    "upgradeStateListener download apk file success"
                )
            }
        }
        /**
         * 已经接入Bugly用户改用上面的初始化方法,不影响原有的crash上报功能;
         * init方法会自动检测更新，不需要再手动调用Beta.checkUpdate(),如需增加自动检查时机可以使用Beta.checkUpdate(false,false);
         * 参数1： applicationContext
         * 参数2：appId
         * 参数3：是否开启debug
         */
        Bugly.init(application, BUGLY_APPID, false)

        //rutosize 开启适配fragment
        AutoSizeConfig.getInstance().isCustomFragment = true;
        SmartShow.init(application)

        BaseConfig().init(application)
        initLog()
    }

}