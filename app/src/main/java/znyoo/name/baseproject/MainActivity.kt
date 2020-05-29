package znyoo.name.baseproject

import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.navOptions
import kotlinx.android.synthetic.main.content_main.*
import me.majiajie.pagerbottomtabstrip.NavigationController
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener
import znyoo.name.base.base.BaseActivity
import znyoo.name.base.extension.exit

class MainActivity : BaseActivity() {

    lateinit var mTabController: NavigationController
    lateinit var mNavController: NavController

    override fun getLayoutId(): Int = R.layout.activity_main


    override fun initOnClick() {
        //系统返回键监
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (mTabController.selected == 0) {
                    exit()
                } else {
                    mTabController.setSelect(0)
                    //返回首页，并且将栈内其他fragment销毁
                    //// 第二个参数 inclusive 表示是否把指定 destinationId 对应的 Fragment 也弹出栈
                    mNavController.popBackStack(R.id.firstFragment, false)
                    mNavController.graph.startDestination
                }
            }
        })

        //底部
        mTabController.addTabItemSelectedListener(object : OnTabItemSelectedListener {
            override fun onSelected(index: Int, old: Int) {
                val action = navOptions {
                    anim {
                        enter = androidx.navigation.ui.R.anim.abc_popup_enter
                        exit = androidx.navigation.ui.R.anim.abc_popup_exit
                        popEnter = androidx.navigation.ui.R.anim.abc_popup_enter
                        popExit = androidx.navigation.ui.R.anim.abc_popup_exit
                    }
                }
                val id = when (index) {
                    0 -> R.id.firstFragment
                    1 -> R.id.twoFragment
                    2 -> R.id.threeFragment
                    3 -> R.id.fourFragment
                    else -> R.id.firstFragment
                }
                mNavController.navigate(
                    id,
                    null,
                    action
                )
            }

            override fun onRepeat(index: Int) {
            }
        })
    }

    override fun initData() {
        mNavController = findNavController(R.id.nav_host_fragment)

        initNavigationBottom()
    }

    /**
     * 初始化底部导航栏
     */
    private fun initNavigationBottom() {
        mTabController = nav_view.material()
            .addItem(
                android.R.drawable.ic_menu_call,
                resources.getString(R.string.item_one),
                resources.getColor(R.color.qmui_btn_blue_bg)
            )
            .addItem(
                android.R.drawable.ic_menu_add,
                resources.getString(R.string.item_two),
                resources.getColor(R.color.qmui_btn_blue_bg)
            )
            .addItem(
                android.R.drawable.ic_menu_agenda,
                resources.getString(R.string.item_three),
                resources.getColor(R.color.qmui_btn_blue_bg)
            )
            .addItem(
                android.R.drawable.ic_menu_camera,
                resources.getString(R.string.item_four),
                resources.getColor(R.color.qmui_btn_blue_bg)
            )
            .setDefaultColor(resources.getColor(R.color.black20))
            .build()
    }
}
