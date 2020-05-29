/**
 * Copyright (C), 2017-2019, XXX有限公司
 * FileName: ToastExt
 * Author:   kiwilss
 * Date:     2019-09-17 10:17
 * Description: {DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package znyoo.name.base.extension

import android.content.Context
import androidx.fragment.app.Fragment
import com.coder.zzq.smartshow.toast.SmartToast

/**
 *@FileName: ToastExt
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2019-09-17
 * @desc   : {DESCRIPTION}
 */
/** toast相关 **/
fun Context.toast(msg: CharSequence?) = SmartToast.show(msg)

fun Context.toast(res: Int) = SmartToast.show(res)

fun Context.toastS(msg: CharSequence?) = SmartToast.success(msg)

fun Context.toastS(res: Int) = SmartToast.success(res)

fun Context.toastE(msg: CharSequence?) = SmartToast.error(msg)

fun Context.toastE(res: Int) = SmartToast.error(res)



fun Context.toastL(msg: CharSequence) = SmartToast.showLong(msg)

fun Context.toastL(res: Int) = SmartToast.showLong(res)



fun Fragment.toast(msg: CharSequence?) = SmartToast.show(msg)

fun Fragment.toast(res: Int) = SmartToast.show(res)

fun Fragment.toastS(msg: CharSequence?) = SmartToast.success(msg)

fun Fragment.toastS(res: Int) = SmartToast.success(res)

fun Fragment.toastE(msg: CharSequence?) = SmartToast.error(msg)

fun Fragment.toastE(res: Int) = SmartToast.error(res)


fun Fragment.toastL(msg: CharSequence) = SmartToast.showLong(msg)

fun Fragment.toastL(res: Int) = SmartToast.showLong(res)
