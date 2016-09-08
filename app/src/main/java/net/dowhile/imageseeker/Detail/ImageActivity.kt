package net.dowhile.imageseeker.Detail

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import net.dowhile.imageseeker.R
import java.util.*

class ImageActivity : AppCompatActivity() {
    private var mRecyclerView: RecyclerView? = null
    private var mBlurView: ImageView? = null
    private val mList = ArrayList<Int>()
    private var mCardScaleHelper: CardScaleHelper? = null
    private var mBlurRunnable: Runnable? = null
    private var mLastPos = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        /*取出Intent中附加的数据*/
        val titleStr = intent.getStringExtra("title")
        val url = intent.getStringExtra("url")
        title = titleStr
        init()
    }

    private fun init() {
//        for (i in 0..9) {
//            mList.add(R.drawable.pic4)
//            mList.add(R.drawable.pic5)
//            mList.add(R.drawable.pic6)
//        }

        mRecyclerView = findViewById(R.id.recyclerView) as RecyclerView?
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        mRecyclerView!!.layoutManager = linearLayoutManager
        mRecyclerView!!.adapter = CardAdapter(mList)
        // mRecyclerView绑定scale效果
        mCardScaleHelper = CardScaleHelper()
        mCardScaleHelper!!.setCurrentItemPos(2)
        mCardScaleHelper!!.attachToRecyclerView(mRecyclerView)

        initBlurBackground()
    }

    private fun initBlurBackground() {
        mBlurView = findViewById(R.id.blurView) as ImageView?
        mRecyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    notifyBackgroundChange()
                }
            }
        })

        notifyBackgroundChange()
    }

    private fun notifyBackgroundChange() {
        if (mLastPos == mCardScaleHelper!!.getCurrentItemPos()) return
        mLastPos = mCardScaleHelper!!.getCurrentItemPos()
        val resId = mList[mCardScaleHelper!!.getCurrentItemPos()]
        mBlurView!!.removeCallbacks(mBlurRunnable)
        mBlurRunnable = Runnable {
            val bitmap = BitmapFactory.decodeResource(resources, resId)
            ViewSwitchUtils.startSwitchBackgroundAnim(mBlurView, BlurBitmapUtils.getBlurBitmap(mBlurView!!.context, bitmap, 15))
        }
        mBlurView!!.postDelayed(mBlurRunnable, 500)
    }

}

class MainActivity : AppCompatActivity() {



}
