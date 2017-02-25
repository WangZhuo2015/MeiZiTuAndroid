/*******************************************************************************
 * Copyright 2014 Sergey Tarasevich

 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

 * http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.dowhile.imageseeker.Detail.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager

import net.dowhile.imageseeker.Detail.Fragments.ImageGridFragment
import net.dowhile.imageseeker.Detail.Fragments.ImageListFragment
import net.dowhile.imageseeker.Model.ItemData
import net.dowhile.imageseeker.Network.ServiceProxy
import net.dowhile.imageseeker.R

import java.lang.reflect.Array

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
class ComplexImageActivity : FragmentActivity() {

    private var pager: ViewPager? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_complex)
        val titleStr = intent.getStringExtra("title")
        val url = intent.getStringExtra("url")
        val pagerPosition = savedInstanceState?.getInt(STATE_POSITION) ?: 0

        pager = findViewById(R.id.pager) as ViewPager
        pager!!.adapter = ImagePagerAdapter(supportFragmentManager,url)
        pager!!.currentItem = pagerPosition
    }




    public override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(STATE_POSITION, pager!!.currentItem)
    }

    private inner class ImagePagerAdapter internal constructor(fm: FragmentManager,dataUrl: String) : FragmentPagerAdapter(fm) {

        internal var listFragment: Fragment
        internal var gridFragment: Fragment
        internal var url: String
        var dataArray: kotlin.Array<ItemData>?  = null
        fun loadData(url:String,complete:()->Unit){
            ServiceProxy.getImages(url,{ data, error ->
                dataArray = data
                complete()
            })
        }

        init {
            url = dataUrl
            listFragment = ImageListFragment()
            gridFragment = ImageGridFragment()
            loadData(url,{
                (gridFragment as ImageGridFragment).setData(dataArray)
            })
        }

        override fun getCount(): Int {
            return 2
        }

        override fun getItem(position: Int): Fragment? {
            when (position) {
                0 -> return listFragment
                1 -> return gridFragment
                else -> return null
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when (position) {
                0 -> return getString(R.string.title_list)
                1 -> return getString(R.string.title_grid)
                else -> return null
            }
        }
    }

    companion object {

        private val STATE_POSITION = "STATE_POSITION"
    }
}