package znyoo.name.baseproject.ui.base

import android.os.Bundle
import android.view.View
import android.view.ViewStub
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.ObjectUtils
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import znyoo.name.base.R
import znyoo.name.base.base.BaseFragment
import znyoo.name.base.base.BaseViewModel
import znyoo.name.base.extension.LoginClick
import znyoo.name.base.extension.gone
import znyoo.name.base.extension.startActivity
import znyoo.name.base.extension.visible
import znyoo.name.baseproject.SampleApplicationLike
import znyoo.name.baseproject.ui.activity.LoginActivity
import znyoo.name.baseproject.ui.custom.CustomLoadingView
import java.security.InvalidParameterException

/**
 *  created by dhkyhb
 *  date: 2020/5/11 20:27
 *  description:
 *  link:
 */
abstract class BaseVmFragment : BaseFragment() {


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        initVm()
        startObserve()
        handleError()

        super.onViewCreated(view, savedInstanceState)
    }

    /**
     *  Fragment 都会检索包含它们的 Activity。这样，当这不同 Fragment 各自获取 ViewModelProvider 时，
     *  它们会收到相同的 SharedViewModel 实例（其范围限定为该 Activity）。
     *
     *  SavedStateViewModelFactory用于资源限制导致的Activity销毁重建 数据恢复
     *  viewmodel数据恢复仅用于配置更改，例如横竖屏
     *
     *  如果viewmodel有参数则继承AbstractSavedStateViewModelFactory实现自己的工厂类.
     */
//    @Deprecated(message = "init Viewmodel by dagger2")
//    fun initVm() {
//        mViewModel = providerVMClass()?.let {
//            ViewModelProvider(this, SavedStateViewModelFactory(SampleApplicationLike.getInstance().application, this))[it]
//        } ?: throw InvalidParameterException("Invalid Viewmodel Class")
//    }

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
    protected fun hideLoadErrorView() {
        if (!ObjectUtils.isEmpty(loadErrorView)) {
            loadErrorView?.gone()
        }
    }

    /**
     * 隐藏无内容View
     */
    protected fun hideNoContentView() {
        if (!ObjectUtils.isEmpty(noContentView)) {
            noContentView?.gone()
        }
    }

    /**
     * 显示加载
     */
    fun showLoading() {
        loadingView?.run {
            dismiss()
            show()
        } ?: activity?.run {
            loadingView = XPopup.Builder(this)
                .hasShadowBg(true)
                .dismissOnBackPressed(false)
                .dismissOnTouchOutside(false)
                .asCustom(CustomLoadingView(this).setTitle(resources.getString(R.string.load_message)))
                .show()
        }
    }

    /**
     * 隐藏加载
     */
    fun hideLoading() {
        loadingView?.run {
            dismiss()
        }
    }

    abstract fun startObserve()

    abstract fun handleError()

}