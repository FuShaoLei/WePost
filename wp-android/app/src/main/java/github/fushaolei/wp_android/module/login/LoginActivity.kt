package github.fushaolei.wp_android.module.login

import android.content.Intent
import github.fushaolei.lib.test.base.ABaseActivity
import github.fushaolei.lib.test.utils.ACache
import github.fushaolei.lib.test.utils.ALog
import github.fushaolei.lib.test.utils.ARetrofit
import github.fushaolei.lib.test.utils.AScope
import github.fushaolei.wp_android.R
import github.fushaolei.wp_android.api.ApiService
import github.fushaolei.wp_android.constant.Constant
import github.fushaolei.wp_android.entity.Reply
import github.fushaolei.wp_android.module.main.MainActivity
import kotlinx.android.synthetic.main.act_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LoginActivity : ABaseActivity() {
    private val scope: CoroutineScope = AScope.getScope()
    private val service = ARetrofit.getService(Constant.HOST, ApiService::class.java)

    override fun getLayoutId(): Int {
        return R.layout.act_login
    }

    override fun initView() {
        btn_login.setOnClickListener {
            var account = et_account.text.toString()
            var password = et_password.text.toString()
            if (account.isNotEmpty() || password.isNotEmpty()) {
                ALog.show("account = ${account},password = ${password}")
                scope.launch {
                    var reply: Reply<String> = service!!.login(account, password)

                    if (reply.code == 200) {
                        ACache.save(Constant.TOKEN, reply.data)
                        startActivity(Intent(
                            this@LoginActivity,
                            MainActivity::class.java))
                        finish()
                    }
                }
            }
        }
    }


}