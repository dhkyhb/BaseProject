package znyoo.name.base.manage

import znyoo.name.base.base.BaseActivity

/**
 *  created by dhkyhb
 *  date: 2020/5/11 20:39
 *  description: 活动管理类
 *  link:
 */
class ActivityCollector private constructor() {

    companion object {
        fun getInstance() = ActivityCollectorSingle.instance
    }

    private val mActivityTools = arrayListOf<BaseActivity>()

    /**
     * @param activity 添加该activity
     */
    fun addActivity(activity: BaseActivity) {
        if (mActivityTools.contains(activity)) return
        mActivityTools.add(activity)
    }

    /**
     * @param activity 移除该activity
     */
    fun removeActivity(activity: BaseActivity) {
        if (mActivityTools.contains(activity)) {
            mActivityTools.remove(activity)
        }
    }

    /**
     * @param activity 移除该activity并关闭
     */
    fun removeActivityAndFinish(activity: BaseActivity) {
        if (mActivityTools.contains(activity)) {
            mActivityTools.remove(activity)
            activity.finish()
        }
    }

    /**
     * 移除所有activity
     */
    fun finishAll() {
        mActivityTools.forEach {
            it.finish()
        }
    }

    fun finishByCls(clz: Class<*>) {
        mActivityTools.forEach {
            if (clz === it.javaClass)
                it.finish()
        }
    }

    private object ActivityCollectorSingle {
        val instance = ActivityCollector()
    }

}