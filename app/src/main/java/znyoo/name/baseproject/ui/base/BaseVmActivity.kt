package znyoo.name.baseproject.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.ViewStub
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.ObjectUtils
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import znyoo.name.base.R
import znyoo.name.base.base.BaseActivity
import znyoo.name.base.base.BaseViewModel
import znyoo.name.base.extension.*
import znyoo.name.baseproject.di.Injectable
import znyoo.name.baseproject.ui.activity.LoginActivity
import znyoo.name.baseproject.ui.custom.CustomLoadingView
import znyoo.name.baseproject.viewmodel.LoginViewModel

/**
 *  created by dhkyhb
 *  date: 2020/5/9 16:30
 *  description: 使界面控制器（Activity 和 Fragment）尽可能保持精简。它们不应试图获取自己的数据，
 *                  而应使用 ViewModel 执行此操作，并观察 LiveData 对象以将更改体现到视图中。
 *  link:
 */
abstract class BaseVmActivity<VM : BaseViewModel> : BaseActivity(), Injectable{

    var mViewModel: VM? = null

    private val loadMessage  = "加载中"

    /**
     * Activity中由于服务器异常导致加载失败显示的布局。
     */
    private var loadErrorView: View? = null
    /**
     * Activity中显示加载等待的控件。
     */
    protected var loadingView: BasePopupView? = null
    /**
     * Activity中当界面上没有任何内容时展示的布局。
     */
    private var noContentView: View? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        initVM()

        startObserve()
        handleError()

    }

    /**
     * 初始化Viewmodel
     */
    abstract fun initVM()

    /**
     * 处理Error
     */
    fun handleError(){}

    /**
     * 监听VM返回
     */
    fun startObserve(){}

    /**
     * 显示加载
     */
    override fun showLoading() {
        loadingView?.run {
            dismiss()
        }

        loadingView = XPopup.Builder(this)
            .hasShadowBg(true)
            .dismissOnBackPressed(false)
            .dismissOnTouchOutside(false)
            .asCustom(CustomLoadingView(this).setTitle(resources.getString(R.string.load_message)))
            .show()
    }

    /**
     * 隐藏加载
     */
    override fun hideLoading() {
        loadingView?.run {
            dismiss()
        }
    }

    /**
     * 当Activity中的加载内容服务器返回失败，通过此方法显示提示界面给用户。
     *
     * @param tip
     * 界面中的提示信息
     */
    protected fun showLoadErrorView(tip: String) {
        if (!ObjectUtils.isEmpty(loadErrorView)) {
            loadErrorView?.visible()
            return
        }

        if (!ObjectUtils.isEmpty(rootView)) {
            val viewStub = rootView?.findViewById<ViewStub>(R.id.loadErrorText)
            if (viewStub != null) {
                loadErrorView = viewStub.inflate()
                val loadErrorText = loadErrorView?.findViewById<TextView>(R.id.loadErrorText)
                loadErrorText?.text = tip
            }
        }
    }

    /**
     * 当Activity中的加载内容服务器返回失败，通过此方法显示提示界面给用户。
     *
     * @param tip
     * 界面中的提示信息
     */
    protected fun showNoContentView(tip: String) {
        if (!ObjectUtils.isEmpty(noContentView)) {
            noContentView?.visible()
            return
        }

        if (!ObjectUtils.isEmpty(rootView)) {
            val viewStub = rootView?.findViewById<ViewStub>(R.id.noContentView)
            if (viewStub != null) {
                noContentView = viewStub.inflate()
                val loadErrorText = noContentView?.findViewById<TextView>(R.id.noContentText)
                loadErrorText?.text = tip
            }
        }
    }

    /**
     * 隐藏网络错误加载View
     */
    protected fun hideLoadErrorView(){
        if (!ObjectUtils.isEmpty(loadErrorView)) {
            loadErrorView?.gone()
        }
    }

    /**
     * 隐藏无内容View
     */
    protected fun hideNoContentView(){
        if (!ObjectUtils.isEmpty(noContentView)) {
            noContentView?.gone()
        }
    }

    fun View.loginClick(action: (v: View) -> Unit) {
        LoginClick(action,
        action2 = {
            gotoLogin()
        })
    }

    private fun gotoLogin() {
        startActivity<LoginActivity>()
    }

}