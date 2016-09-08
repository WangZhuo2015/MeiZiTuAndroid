package net.dowhile.imageseeker.Detail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import java.util.*

/**
 * Created by wz on 16/9/8.
 */
internal class CardAdapter(mList: List<Int>) : RecyclerView.Adapter<CardAdapter.ViewHolder>() {
    private val mList = ArrayList<Int>()
    private val mCardAdapterHelper = CardAdapterHelper()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_card_item, parent, false)
        mCardAdapterHelper.onCreateViewHolder(parent, itemView)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        mCardAdapterHelper.onBindViewHolder(holder.itemView, position, itemCount)
        holder.mImageView.setImageResource(mList[position])
        holder.mImageView.setOnClickListener { ToastUtils.show(holder.mImageView.context, "" + position) }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mImageView: ImageView

        init {
            mImageView = itemView.findViewById(R.id.imageView) as ImageView
        }

    }

}