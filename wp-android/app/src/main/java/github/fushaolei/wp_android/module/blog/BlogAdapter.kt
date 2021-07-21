package github.fushaolei.wp_android.module.blog

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import github.fushaolei.lib.test.Alab
import github.fushaolei.lib.test.base.ARecyclerAdapter
import github.fushaolei.lib.test.base.ARecyclerViewHolder
import github.fushaolei.wp_android.R
import github.fushaolei.wp_android.constant.Constant
import github.fushaolei.wp_android.entity.Post

class BlogAdapter(dataList: MutableList<Post>) :
    ARecyclerAdapter<Post, BlogAdapter.ViewHolder>(
        dataList) {
    override fun convert(holder: ViewHolder, item: Post) {
        // TODO 头像
        Glide.with(Alab.getAppContext())
            .load(Constant.IMG_PRE + item.user.avator)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(holder.avator)
        holder.name.text = item.user.name
        holder.date.text = item.date.toString()
        holder.content.text = item.content
    }

    override fun getItemLayout(): Int {
        return R.layout.item_blog
    }

    override fun onReturnVH(view: View): ViewHolder {
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : ARecyclerViewHolder(itemView) {
        var avator: ImageView = itemView.findViewById(R.id.iv_avator)
        var name: TextView = itemView.findViewById(R.id.tv_name)
        var date: TextView = itemView.findViewById(R.id.tv_date)
        var content: TextView = itemView.findViewById(R.id.tv_content)
    }


}