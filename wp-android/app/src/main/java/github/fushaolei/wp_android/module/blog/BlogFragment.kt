package github.fushaolei.wp_android.module.blog

import github.fushaolei.lib.test.base.ABaseFragment
import github.fushaolei.lib.test.utils.ACache
import github.fushaolei.lib.test.utils.ALog
import github.fushaolei.lib.test.utils.ARetrofit
import github.fushaolei.lib.test.utils.AScope
import github.fushaolei.wp_android.R
import github.fushaolei.wp_android.api.ApiService
import github.fushaolei.wp_android.constant.Constant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class BlogFragment : ABaseFragment() {
    private val scope: CoroutineScope = AScope.getScope()
    private val service = ARetrofit.getService(Constant.HOST, ApiService::class.java)

    override fun getLayoutId(): Int {
        return R.layout.frag_blog
    }

    override fun initView() {
        scope.launch {
            var token = ACache.get(Constant.TOKEN)
            var dataList = service!!.getPostList(1, token!!)
            if (dataList.code == 200) {
                dataList.data.forEach {
                    ALog.show(it.content)
                }
            }
        }
    }
}