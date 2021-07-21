package github.fushaolei.wp_android.module.edit

import android.view.WindowManager
import github.fushaolei.lib.test.base.ABaseActivity
import github.fushaolei.lib.test.utils.*
import github.fushaolei.wp_android.R
import github.fushaolei.wp_android.api.ApiService
import github.fushaolei.wp_android.constant.Constant
import kotlinx.android.synthetic.main.act_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class EditActivity : ABaseActivity() {
    private val scope: CoroutineScope = AScope.getScope()
    private val service = ARetrofit.getService(Constant.HOST, ApiService::class.java)
    override fun getLayoutId(): Int {
        return R.layout.act_edit
    }

    override fun initView() {

        // editview获得焦点
        et_post.isFocusable = true;
        et_post.isFocusableInTouchMode = true;
        et_post.requestFocus();
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)


        btn_publish.setOnClickListener {
            var text = et_post.text.toString()
            if (text.isEmpty()) return@setOnClickListener
            scope.launch {
                ALog.show(text)
                val token = ACache.get(Constant.TOKEN)
                var reply = service!!.createPost(text, token!!)
                if (reply.code == 200) {
                    AToast.show("操作成功！")
                    finish()
                }
            }
        }
    }
}