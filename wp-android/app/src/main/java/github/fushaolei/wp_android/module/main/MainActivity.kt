package github.fushaolei.wp_android.module.main

import androidx.fragment.app.Fragment
import github.fushaolei.lib.test.base.ABaseActivity
import github.fushaolei.wp_android.R
import github.fushaolei.wp_android.module.blog.BlogFragment
import github.fushaolei.wp_android.module.mine.MineFragment
import kotlinx.android.synthetic.main.act_main.*

class MainActivity : ABaseActivity() {
    private val fragments by lazy {
        arrayOf(BlogFragment(), MineFragment())
    }

    override fun getLayoutId(): Int {
        return R.layout.act_main
    }

    override fun initView() {
        bottom_nav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.tab_blog -> switchFragment(0)
                R.id.tab_mine -> switchFragment(1)
            }
            true
        }
        switchFragment(0)
    }


    /**
     * 切换fragmnet页面
     */
    private fun switchFragment(i: Int) {
        var ft = supportFragmentManager.beginTransaction()
        for ((index, f: Fragment) in fragments.withIndex()) {
            if (index != i && f.isAdded && f.isVisible) {
                // 如果其他有显示的，就将其隐藏
                ft.hide(f)
            } else if (index == i && !f.isAdded) {
                // 如果下标相同，但没有添加的，就将其添加
                ft.add(R.id.fl_container, f)
            }
        }
        ft.show(fragments[i]).commit()
    }
}