package github.fushaolei.wp_android

import android.content.Intent
import github.fushaolei.lib.test.base.ABaseActivity
import github.fushaolei.lib.test.utils.ACache
import github.fushaolei.lib.test.utils.AScope
import github.fushaolei.wp_android.constant.Constant
import github.fushaolei.wp_android.module.login.LoginActivity
import github.fushaolei.wp_android.module.main.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : ABaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.act_splash
    }

    override fun initView() {
        var scop = AScope.getScope()
        scop.launch {
            delay(1500)
            if (ACache.get(Constant.TOKEN) == null) {
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            } else {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }
            finish()
        }
    }

}