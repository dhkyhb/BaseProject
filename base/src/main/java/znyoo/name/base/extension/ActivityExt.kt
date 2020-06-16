package znyoo.name.base.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.permissionx.guolindev.PermissionBuilder
import com.permissionx.guolindev.PermissionX
import znyoo.name.base.common.toBundle
import znyoo.name.base.livedate.LifecycleHandler
import java.io.InvalidClassException
import java.security.InvalidParameterException

/**
 *  created by dhkyhb
 *  date: 2020/5/18 17:47
 *  description: Activity相关
 *  link:
 */


inline fun <reified T> Fragment.startActivity(flag: Int = -1, bundle: Array<out Pair<String, Any?>>? = null) {
    activity?.startActivity<T>(flag, bundle)
}

inline fun <reified T> Fragment.startActivityForResult(flag: Int = -1, bundle: Array<out Pair<String, Any?>>? = null, requestCode: Int = -1) {
    activity?.startActivityForResult<T>(flag, bundle, requestCode)
}

inline fun <reified T> Context.startActivity(flag: Int = -1, bundle: Array<out Pair<String, Any?>>? = null, requestCode: Int = -1) {
    val intent = Intent(this, T::class.java).apply {
        if (flag != -1) {
            this.addFlags(flag)
        } else if (this !is Activity) {
            this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        if (bundle != null) bundle.toBundle()?.let { putExtras(it) }
    }
    startActivity(intent)
}

inline fun <reified T> View.startActivity(flag: Int = -1, bundle: Array<out Pair<String, Any?>>? = null) {
    context.startActivity<T>(flag, bundle)
}

inline fun <reified T> View.startActivityForResult(flag: Int = -1, bundle: Array<out Pair<String, Any?>>? = null, requestCode: Int = -1) {
    (context as Activity).startActivityForResult<T>(flag, bundle, requestCode)
}

inline fun <reified T> Activity.startActivityForResult(flag: Int = -1, bundle: Array<out Pair<String, Any?>>? = null, requestCode: Int = -1) {
    val intent = Intent(this, T::class.java).apply {
        if (flag != -1) {
            this.addFlags(flag)
        }
        if (bundle != null) bundle.toBundle()?.let { putExtras(it) }
    }
    startActivityForResult(intent, requestCode)
}

/**
 * [permissions] - 需要请求的权限
 * [PermissionBuilder] - 对请求权限操作后的处理
 *       -  request 请求后的处理方法
 *       -  onExplainRequestReason 请求被拒绝之后的询问客户 继续请求方法
 */
fun AppCompatActivity?.checkPermission(vararg permissions: String): PermissionBuilder {
    return this?.run {
        PermissionX.init(this)
            .permissions(*permissions)
    } ?: throw InvalidParameterException("Invalid activity!")
}

fun AppCompatActivity?.finishDelay(delay: Long = 1) {
    this?.run {
        LifecycleHandler(this).postDelayed({ finish() }, delay)
    }
}

////post, postDelay
fun AppCompatActivity?.post(action: ()->Unit){
    LifecycleHandler(this).post { action() }
}
//
fun AppCompatActivity?.postDelay(delay:Long = 0, action: ()->Unit){
    LifecycleHandler(this).postDelayed({ action() }, delay)
}

//view model
fun <T: ViewModel> AppCompatActivity.getVM(clazz: Class<T>) = ViewModelProvider(this).get(clazz)