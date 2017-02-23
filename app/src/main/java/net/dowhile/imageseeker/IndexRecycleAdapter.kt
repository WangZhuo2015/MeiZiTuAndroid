package net.dowhile.imageseeker

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.index_item.view.*
import net.dowhile.imageseeker.Model.ListData
import net.dowhile.imageseeker.Network.ServiceProxy
import org.jetbrains.anko.toast

class IndexRecycleAdapter(internal val didSelectedAtPos: (data: ListData) -> Unit) : RecyclerView.Adapter<IndexRecycleAdapter.ViewHolder>() {
    private var mItems = arrayOf<ListData>()
    internal var mContext: Context? = null
    private var currentPage = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewHolder = ViewHolder(layoutInflater.inflate(R.layout.index_item, parent, false))
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mItems[position]
        holder.title.text = item.title
        Glide.with(mContext)
                .load(item.img)
                .placeholder(R.drawable.thumb)
                .into(holder.image)
        with (holder.container) {
            tag = item
            setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View) {
                    didSelectedAtPos(mItems[position])
                }
            })
        }
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

//    internal fun add() {
//        for (i in 0..30){
//            val data = ListData("{\"title\":\"这里测试文字哦哈哟\",\"img\":\"http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg\",\"url\":\"http://www.mzitu.com/72867\"}")
//            mItems.add(0,data)
//        }
//        notifyDataSetChanged()
//    }

    /**
    获取数据方法

    - parameter name:     菜名
    - parameter loadMore: 是否加载更多
     */
    internal fun loadList(loadMore:Boolean = true,complete:(()->Unit)?){
        //TODO -:一次获取详细数据

        //如果是刷新
        if (!loadMore) {
            currentPage = 1
            mItems = arrayOf<ListData>()
            //notifyDataSetChanged()
        }
        ServiceProxy.getList(currentPage) { res, error ->
            if (error != null) {
                //出错
                mContext!!.toast("错误 $error")
                currentPage = 1
                mItems = arrayOf<ListData>()
            }else if (res!!.count() == 0){
                //No More
                mContext!!.toast("没有更多了")
            }else{
                currentPage += 1
                mItems += res!!
                mContext!!.toast("成功")
                //成功
            }
            notifyDataSetChanged()
            if (complete != null) complete()
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image = view.index_item_image
        var container = view.index_item_root_view
        var title = view.index_item_title
    }
}