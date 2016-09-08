package net.dowhile.imageseeker.Network

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Recycler
import android.view.View

/**
 * Created by wz on 16/9/8.
 */
class IndexLayoutManager: GridLayoutManager {
    constructor(context: Context,int: Int):super(context,int)

    override fun onMeasure(recycler: Recycler?, state: RecyclerView.State?, widthSpec: Int, heightSpec: Int) {
        super.onMeasure(recycler, state, widthSpec, heightSpec)
        val view = recycler!!.getViewForPosition(0)
        if (view != null) {
            measureChild(view, widthSpec, heightSpec)
            val measuredWidth = View.MeasureSpec.getSize(widthSpec)
            val measuredHeight = view!!.getMeasuredHeight()
            setMeasuredDimension(measuredWidth, measuredHeight)
        }
    }
}