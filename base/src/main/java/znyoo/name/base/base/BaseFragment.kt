package znyoo.name.base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 *  created by dhkyhb
 *  date: 2020/5/11 20:27
 *  description: 基础Fragment
 *  link:
 */
abstract class BaseFragment : Fragment(){

    /**
     * Activity中inflate出来的布局。
     */
    protected var rootView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutResId(), container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initData()
        initOnClick()
        super.onViewCreated(view, savedInstanceState)
    }

    /**
     * 获取资源文件
     */
    abstract fun getLayoutResId(): Int

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 初始化监听事件
     */
    abstract fun initOnClick()

}