package net.dowhile.imageseeker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import net.dowhile.imageseeker.Detail.ImageActivity
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.toast
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
            adapter.loadList(false,{
                swipe_container.isRefreshing = false
                print("***************************")
                print(swipe_container.isRefreshing)
                print("***************************")
            })
//            Handler().postDelayed({
//                kotlin.run {
//                 adapter.loadList(false,{
//                        swipe_container.isRefreshing = false
//                        print(swipe_container.isRefreshing)
//                 })
//                }
//            },3000)
        }

        swipe_container.post {
            kotlin.run {
                swipe_container.isRefreshing = true
            }
        }
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

class DividerGridItemDecoration(mainActivity: MainActivity) : RecyclerView.ItemDecoration() {

}
