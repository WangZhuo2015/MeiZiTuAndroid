package net.dowhile.imageseeker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.avos.avoscloud.*
import kotlinx.android.synthetic.main.activity_main.*
import net.dowhile.imageseeker.Detail.ImageActivity
import net.youmi.android.AdManager
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.toast
class MainActivity : AppCompatActivity() {
    var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //有米广告
        val appId = "542faf057d621231"
        val appSecret = "835f70af30806c98"
        val isTestModel = true
        val isEnableYoumiLog = true
        AdManager.getInstance(this).init(appId, appSecret , isTestModel, isEnableYoumiLog);


        AVOSCloud.initialize(this, "xei6QryNnfTUrW6fStLQiBb2-gzGzoHsz", "3JFWYTsUbJlnpSIRFegD6EEg")
        AVAnalytics.enableCrashReport(this, true);
        val testObject = AVObject("TestObject")
        testObject.put("words", "Hello World!")
        testObject.saveInBackground(object : SaveCallback() {
            override fun done(e: AVException?) {
                if (e == null) {
                    Log.d("saved", "success!")
                }
            }
        })

        //设置刷新时动画的颜色，可以设置4个
        val layoutManager = GridLayoutManager(this,2)
        //设置布局管理器
        myRecyclerView.layoutManager = layoutManager
        layoutManager
        //设置为垂直布局，这也是默认的
        layoutManager.orientation = OrientationHelper.VERTICAL
        val adapter = IndexRecycleAdapter({data ->
            val intent = Intent(this, ImageActivity::class.java)
            intent.putExtra("title",data.title)
            intent.putExtra("url",data.url)
            this.startActivity(intent)
        })


        //设置Adapter
        myRecyclerView.adapter = adapter
        adapter.mContext = this
        //设置分隔线
        //myRecyclerView.addItemDecoration(DividerGridItemDecoration(this))
        //设置增加或删除条目的动画
        myRecyclerView.itemAnimator = DefaultItemAnimator()
        swipe_container.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light)
        swipe_container.onRefresh {
            if (!isLoading) {
                isLoading = true
                adapter.loadList(false, {
                    swipe_container.isRefreshing = false
                    isLoading = false
                })
            }else toast("别着急,加载ing")
        }
        //上拉
        myRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                if (lastVisibleItemPosition + 1 == adapter.itemCount) {
                    if (swipe_container.isRefreshing) {
                        adapter.notifyItemRemoved(adapter.itemCount)
                        return
                    }
                    if (!isLoading) {
                        swipe_container.isRefreshing = true
                        isLoading = true
                        adapter.loadList(true, {
                            swipe_container.isRefreshing = false
                            isLoading = false
                        })
                    }else toast("别着急,加载ing")
                }
            }
        })
        
        //开始刷新
        swipe_container.post {
            kotlin.run {
                swipe_container.isRefreshing = true
            }
        }
        //读取一次数据
        adapter.loadList(false,{
            swipe_container.post {
                kotlin.run {
                    swipe_container.isRefreshing = false
                }
            }
        })


    }




    internal fun inentToActivity(url:String){
        toast("tapped $url")
    }
}