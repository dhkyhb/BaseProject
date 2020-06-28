package znyoo.name.base.base

import android.app.ActivityManager
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.ViewStub
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.AdaptScreenUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ObjectUtils
import com.gyf.immersionbar.ImmersionBar
import com.gyf.immersionbar.ktx.immersionBar
import com.gyf.immersionbar.ktx.setFitsSystemWindows
import com.qmuiteam.qmui.kotlin.onClick
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.currency_top.*
import znyoo.name.base.R
import znyoo.name.base.common.sp
import znyoo.name.base.extension.click
import znyoo.name.base.extension.startActivity
import znyoo.name.base.extension.visible
import znyoo.name.base.manage.ActivityCollector

/**
 *  created by dhkyhb
 *  date: 2020/5/9 15:15
 *  description: 页面详情去看template_layout.xml
 *  link:
 */
abstract class BaseActivity : AppCompatActivity(), HasAndroidInjector {

    private val TAG = BaseActivity::class.java.simpleName

    /**
     * Activity中inflate出来的布局。
     */
    protected var rootView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doBeforeSetContentView()

        setContentView(getLayoutId())

        setStatusBar()

        //活动管理器添加当前活动
        ActivityCollector.getInstance().addActivity(this)
        initData()

        initOnClick()

        initTopTitle()
        setBackListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.getInstance().removeActivity(this)
    }

    /**
     * [html]https://blankj.com/2018/12/18/android-adapt-screen-killer/#more
     * 适配规则
     */
    override fun getResources(): Resources {
//        宽度适配
        return AdaptScreenUtils.adaptWidth(super.getResources(), 720)
        //按高度适配
//        return AdaptScreenUtils.adaptHeight(super.getResources(), 720)
        //如果某个页面不需要适配、直接返回这个
//        return AdaptScreenUtils.closeAdapt(super.getResources())
    }

    /**
     * 显示加载
     */
    open fun showLoading() {}

    /**
     * 显示加载
     */
    open fun hideLoading() {}

    /**
     * 设置布局文件
     */
    abstract fun getLayoutId(): Int

    /**
     * 初始化点击事件
     */
    abstract fun initOnClick()

    /**
     * 初始化数据
     */
    abstract fun initData()

    fun getRightView(): TextView? = tv_currency_top_right
    fun getTitleView(): TextView? = tv_currency_top_title
    fun getIvBack(): ImageView? = iv_currency_top_back

    /**
     * 布局之前的操作
     */
    open fun doBeforeSetContentView(){}

    /**
     * 设置状态栏
     */
    //lateinit var mImmersionBar: ImmersionBar
    open fun setStatusBar() {
        //设置默认样式
        immersionBar {
            fitsSystemWindows(true)
            statusBarColor(R.color.qmui_config_color_white)
            statusBarDarkFont(true, 0f)
        }
    }

    open fun initTopTitle() {
        tv_currency_top_title?.let {
            it.text = title
        }
    }

    open fun setBackListener() {
        iv_currency_top_back?.let {
            it.click { finish() }
        }
    }

    /**
     * 隐藏软键盘。
     */
    protected fun hideSoftKeyboard() {
        try {
            val view = currentFocus
            if (view != null) {
                val binder = view.windowToken
                val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                manager.hideSoftInputFromWindow(binder, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        } catch (e: Exception) {
            LogUtils.w(TAG, e.message, e)
        }

    }

    /**
     * 显示软键盘。
     */
    protected fun showSoftKeyboard(editText: EditText?) {
        try {
            if (editText != null) {
                editText.requestFocus()
                editText.onClick {  }
                val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                manager.showSoftInput(editText, 0)
            }
        } catch (e: Exception) {
            LogUtils.w(TAG, e.message, e)
        }
    }
}