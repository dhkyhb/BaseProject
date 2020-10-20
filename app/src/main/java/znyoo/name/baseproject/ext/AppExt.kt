package znyoo.name.baseproject.ext

import android.view.View
import com.blankj.utilcode.util.NetworkUtils
import com.lxj.xpopup.interfaces.OnConfirmListener
import com.qmuiteam.qmui.kotlin.throttleClick
import znyoo.name.base.common.enum.ClickType
import znyoo.name.base.common.isLogin
import znyoo.name.base.extension.LoginClick
import znyoo.name.base.extension.startActivity
import znyoo.name.baseproject.R
import znyoo.name.baseproject.ui.activity.LoginActivity
import znyoo.name.baseproject.utils.XpopupUtils

/**
 * @ProjectName:    BaseProject
 * @Package:        znyoo.name.baseproject.commmon
 * @ClassName:      ViewExt
 * @Author:         dhkyhb
 * @CreateDate:     2020/6/27 9:00 PM
 * @Description:     java类作用描述
 */

fun View.loginClick(action: (v: View) -> Unit) {
    LoginClick(action,
        action2 = {
            startActivity<LoginActivity>()
        })
}

/**
 * 通用点击事件
 */
inline fun View.onClick(crossinline clickAction: (v: View) -> Unit, type: ClickType =  ClickType.NORMALCLICK) {
    setOnClickListener {
        throttleClick(1000) {
            if ((type === ClickType.NEEDNET || type === ClickType.NEEDLOGIN) && !NetworkUtils.isConnected()) {
                XpopupUtils.showCuntomPopup(
                    this.context.getString(R.string.hint),
                    this.context.getString(R.string.no_network),
                    OnConfirmListener { }
                )
                return@throttleClick
            }
            if (type === ClickType.NEEDLOGIN && !isLogin()) {
                XpopupUtils.showCuntomPopup(
                    this.context.getString(R.string.hint),
                    this.context.getString(R.string.no_login),
                    OnConfirmListener { startActivity<LoginActivity>() }
                )
                return@throttleClick
            }

            clickAction(this)
        }
    }
}

