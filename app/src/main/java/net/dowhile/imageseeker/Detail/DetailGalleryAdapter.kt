package net.dowhile.imageseeker.Detail

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Gallery
import android.widget.ImageView
import com.bumptech.glide.Glide
import net.dowhile.imageseeker.Model.ItemData
import net.dowhile.imageseeker.Network.ServiceProxy
import net.dowhile.imageseeker.R

/**
 * Created by wz on 16/9/8.
 */
class DetailGalleryAdapter(val url: String) :BaseAdapter(){
    var dataArray:Array<ItemData>?  = null
    var mContext:Context? = null
    override fun getCount(): Int {
        if (dataArray == null) return 0
        else return dataArray!!.count()
    }

    override fun getItem(position: Int): Any {
        return  position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val imageView = ImageView(mContext)
        val item = dataArray!![position]
        Glide.with(mContext)
                .load(item.img)
                .placeholder(R.drawable.thumb)
                .into(imageView)
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        imageView.layoutParams = Gallery.LayoutParams(150,200)
        //val typpedArray = Style
        return imageView
    }

    fun loadData(complete:()->Unit){
        ServiceProxy.getImages(url,{ data,error ->
            dataArray = data
            notifyDataSetChanged()
            complete()
        })
    }

}