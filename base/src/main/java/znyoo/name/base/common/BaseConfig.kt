package znyoo.name.base.common

import android.content.Context

/**
 * @ProjectName:    BaseProject
 * @Package:        znyoo.name.base.common
 * @ClassName:      BaseConfig
 * @Author:         dhkyhb
 * @CreateDate:     2020/6/24 12:04 PM
 * @Description:    类配置文件
 */
class BaseConfig {

    lateinit var context: Context

    fun init(context: Context) {
        this.context = context
    }

}