package znyoo.name.baseproject.utils

import com.lxj.xpopup.XPopup
import com.lxj.xpopup.interfaces.OnCancelListener
import com.lxj.xpopup.interfaces.OnConfirmListener
import znyoo.name.baseproject.R
import znyoo.name.baseproject.SampleApplicationLike

/**
 * @ProjectName:    BaseProject
 * @Package:        znyoo.name.baseproject.utils
 * @ClassName:      XpopupUtils
 * @Author:         dhkyhb
 * @CreateDate:     2020/10/20 3:33 PM
 * @Description:     java类作用描述
 */
class XpopupUtils {

    companion object {

        fun showCuntomPopup(
            title: String,
            content: String,
            onConfirmListener: OnConfirmListener,
            onCancelListener: OnCancelListener = OnCancelListener { },
            hideCancel: Boolean = false
        ) {
            val app = SampleApplicationLike.getInstance().application
            XPopup.Builder(app)
                .dismissOnBackPressed(false)
                .dismissOnTouchOutside(false)
                .asConfirm(
                    title,
                    content,
                    app.getString(R.string.cancel),
                    app.getString(R.string.ensure),
                    onConfirmListener,
                    onCancelListener,
                    hideCancel
                )
                .show()

        }

    }

}