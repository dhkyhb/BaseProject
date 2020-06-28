package znyoo.name.baseproject.ext

import android.view.View
import znyoo.name.base.extension.LoginClick
import znyoo.name.base.extension.startActivity
import znyoo.name.baseproject.ui.activity.LoginActivity

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

