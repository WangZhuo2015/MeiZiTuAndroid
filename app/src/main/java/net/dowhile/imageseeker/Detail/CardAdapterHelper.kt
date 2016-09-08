package net.dowhile.imageseeker.Detail

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

/**
 * Created by wz on 16/9/8.
 */
class CardAdapterHelper {
    private var mPagePadding = 15
    private var mShowLeftCardWidth = 15

    fun onCreateViewHolder(parent: ViewGroup, itemView: View) {
        val lp = itemView.layoutParams as RecyclerView.LayoutParams
        lp.width = parent.width - ScreenUtil.dip2px(itemView.context, 2 * (mPagePadding + mShowLeftCardWidth))
        itemView.layoutParams = lp
    }

    fun onBindViewHolder(itemView: View, position: Int, itemCount: Int) {
        val padding = ScreenUtil.dip2px(itemView.context, mPagePadding)
        itemView.setPadding(padding, 0, padding, 0)
        val leftMarin = if (position == 0) padding + ScreenUtil.dip2px(itemView.context, mShowLeftCardWidth) else 0
        val rightMarin = if (position == itemCount - 1) padding + ScreenUtil.dip2px(itemView.context, mShowLeftCardWidth) else 0
        setViewMargin(itemView, leftMarin, 0, rightMarin, 0)
    }

    private fun setViewMargin(view: View, left: Int, top: Int, right: Int, bottom: Int) {
        val lp = view.layoutParams as ViewGroup.MarginLayoutParams
        if (lp.leftMargin != left || lp.topMargin != top || lp.rightMargin != right || lp.bottomMargin != bottom) {
            lp.setMargins(left, top, right, bottom)
            view.layoutParams = lp
        }
    }

    fun setPagePadding(pagePadding: Int) {
        mPagePadding = pagePadding
    }

    fun setShowLeftCardWidth(showLeftCardWidth: Int) {
        mShowLeftCardWidth = showLeftCardWidth
    }
}
