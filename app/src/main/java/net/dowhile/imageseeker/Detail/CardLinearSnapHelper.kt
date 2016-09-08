package net.dowhile.imageseeker.Detail

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.LinearSnapHelper
import android.view.View

/**
 * Created by wz on 16/9/8.
 */
class CardLinearSnapHelper : LinearSnapHelper() {
    var mNoNeedToScroll = false

    fun calculateDistanceToFinalSnap(layoutManager: RecyclerView.LayoutManager, targetView: View): IntArray {
        if (mNoNeedToScroll) {
            return intArrayOf(0, 0)
        } else {
            return super.calculateDistanceToFinalSnap(layoutManager, targetView)
        }
    }

}