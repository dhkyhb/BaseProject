package znyoo.name.baseproject.ui.custom

import android.content.Context
import com.blankj.utilcode.util.ObjectUtils
import com.lxj.xpopup.core.CenterPopupView
import kotlinx.android.synthetic.main.layout_loading.view.*
import znyoo.name.base.extension.gone
import znyoo.name.base.extension.visible
import znyoo.name.baseproject.R

/**
 *  created by dhkyhb
 *  date: 2020/5/12 14:29
 *  description: 自定义loading
 *  link:
 */
class CustomLoadingView(context: Context) : CenterPopupView(context){

    lateinit var title: String

    override fun getImplLayoutId(): Int {
        return R.layout.layout_loading
    }

    override fun initPopupContent() {
        super.initPopupContent()
        if (ObjectUtils.isEmpty(title)) {
            mTvLoading.gone()
        }else{
            mTvLoading.text = title
            mTvLoading.visible()
        }
    }

    open fun setTitle(title: String): CustomLoadingView {
        this.title = title
        return this
    }

}