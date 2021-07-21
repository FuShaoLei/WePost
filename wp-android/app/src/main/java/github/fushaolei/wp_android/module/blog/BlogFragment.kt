package github.fushaolei.wp_android.module.blog

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import github.fushaolei.lib.test.base.ABaseFragment
import github.fushaolei.lib.test.utils.ACache
import github.fushaolei.lib.test.utils.ALog
import github.fushaolei.lib.test.utils.ARetrofit
import github.fushaolei.lib.test.utils.AScope
import github.fushaolei.wp_android.R
import github.fushaolei.wp_android.api.ApiService
import github.fushaolei.wp_android.constant.Constant
import github.fushaolei.wp_android.entity.Post
import github.fushaolei.wp_android.module.edit.EditActivity
import kotlinx.android.synthetic.main.frag_blog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class BlogFragment : ABaseFragment() {
    private val scope: CoroutineScope = AScope.getScope()
    private val service = ARetrofit.getService(Constant.HOST, ApiService::class.java)
    private lateinit var adapter: BlogAdapter
    private lateinit var mDataList: List<Post>

    override fun getLayoutId(): Int {
        return R.layout.frag_blog
    }

    override fun initView() {

        float_button.setOnClickListener {
            startActivity(Intent(context, EditActivity::class.java))
        }

        val lm = LinearLayoutManager(context)
        lm.orientation = RecyclerView.VERTICAL

        scope.launch {
            val token = ACache.get(Constant.TOKEN)
            val dataList = service!!.getPostList(1, token!!)
            if (dataList.code == 200) {

                dataList.data.forEach {
                    ALog.show(it.toString())
                }

                adapter = BlogAdapter(dataList.data as MutableList<Post>)

                rv_blog.layoutManager = lm
                rv_blog.adapter = adapter
                ALog.show("adapter.itemCount = ${adapter.itemCount}")
                adapter.notifyDataSetChanged()
            }
        }
    }
}