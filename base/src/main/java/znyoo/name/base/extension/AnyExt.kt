package znyoo.name.base.extension

import android.content.Context
import com.blankj.utilcode.util.ToastUtils
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import znyoo.name.base.R
import znyoo.name.base.manage.ActivityCollector
import java.util.concurrent.TimeUnit

/**
 *  created by dhkyhb
 *  date: 2020/5/9 17:37
 *  description:
 *  link:
 */

var isClicked = false
var disposable: Disposable? = null
fun Context.exit() {
    if (!isClicked) {
        isClicked = true
        ToastUtils.showShort(this.resources.getString(R.string.exit_hint))
    } else {
        isClicked = false
        disposable?.run {
            dispose()
            null
        }
        ActivityCollector.getInstance().finishAll()
        this.exit()
        return
    }

    disposable = Observable.just(1)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .delay(2, TimeUnit.SECONDS)
        .subscribe { isClicked = false }
}
