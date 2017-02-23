//package net.dowhile.imageseeker.Detail
//
//import android.content.Context
//import android.support.v7.widget.RecyclerView
//import android.util.AttributeSet
//
///**
// * Created by wz on 16/9/8.
// */
//class SpeedRecyclerView : RecyclerView {
//
//    constructor(context: Context) : super(context) {
//    }
//
//    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
//    }
//
//    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
//    }
//
//    override fun fling(velocityX: Int, velocityY: Int): Boolean {
//        var velocityX = velocityX
//        var velocityY = velocityY
//        velocityX = solveVelocity(velocityX)
//        velocityY = solveVelocity(velocityY)
//        return super.fling(velocityX, velocityY)
//    }
//
//    private fun solveVelocity(velocity: Int): Int {
//        if (velocity > 0) {
//            return Math.min(velocity, FLING_MAX_VELOCITY)
//        } else {
//            return Math.max(velocity, -FLING_MAX_VELOCITY)
//        }
//    }
//
//    companion object {
//        private val FLING_SCALE_DOWN_FACTOR = 0.5f // 减速因子
//        private val FLING_MAX_VELOCITY = 8000 // 最大顺时滑动速度
//    }
//
//}
