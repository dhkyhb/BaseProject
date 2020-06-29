package znyoo.name.base.common

import android.os.Bundle
import android.os.Parcelable
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SPUtils
import java.io.Serializable

/**
 *  created by dhkyhb
 *  date: 2020/5/9 17:39
 *  description: 通用方法文件
 *  link:
 */

fun sp(): SPUtils? {
    return SPUtils.getInstance(SP_NAME)
}

fun isLogin(): Boolean {
    return (sp()?.getString("mid") ?: "").isNotBlank()
}

fun initLog() {
    LogUtils.getConfig().run {
        isLogSwitch = true     //日志开启
        globalTag = APP_TAG//全局tag
        isLogHeadSwitch = true//头部信息开启
        //setDir()            //设置 log 文件存储目录
        //setFilePrefix()     //设置 log 文件前缀
        //setBorderSwitch()   //设置 log 边框开关
        //setSingleTagSwitch()//设置 log 单一 tag 开关（为美化 AS 3.1 的 Logcat）
        //setConsoleFilter()  //设置 log 控制台过滤器
        //setFileFilter()     //设置 log 文件过滤器
        //setStackDeep()      //设置 log 栈深度
        //setStackOffset()    //设置 log 栈偏移
        //setSaveDays()       //设置 log 可保留天数
        //addFormatter()      //新增 log 格式化器
    }
}

/**
 * 数组转bundle
 */
fun Array<out Pair<String, Any?>>.toBundle(): Bundle? {
    return Bundle().apply {
        forEach { it ->
            val value = it.second
            when (value) {
                null -> putSerializable(it.first, null as Serializable?)
                is Int -> putInt(it.first, value)
                is Long -> putLong(it.first, value)
                is CharSequence -> putCharSequence(it.first, value)
                is String -> putString(it.first, value)
                is Float -> putFloat(it.first, value)
                is Double -> putDouble(it.first, value)
                is Char -> putChar(it.first, value)
                is Short -> putShort(it.first, value)
                is Boolean -> putBoolean(it.first, value)
                is Serializable -> putSerializable(it.first, value)
                is Parcelable -> putParcelable(it.first, value)

                is IntArray -> putIntArray(it.first, value)
                is LongArray -> putLongArray(it.first, value)
                is FloatArray -> putFloatArray(it.first, value)
                is DoubleArray -> putDoubleArray(it.first, value)
                is CharArray -> putCharArray(it.first, value)
                is ShortArray -> putShortArray(it.first, value)
                is BooleanArray -> putBooleanArray(it.first, value)

                is Array<*> -> when {
                    value.isArrayOf<CharSequence>() -> putCharSequenceArray(it.first, value as Array<CharSequence>)
                    value.isArrayOf<String>() -> putStringArray(it.first, value as Array<String>)
                    value.isArrayOf<Parcelable>() -> putParcelableArray(it.first, value as Array<Parcelable>)
                }
            }
        }
    }

}