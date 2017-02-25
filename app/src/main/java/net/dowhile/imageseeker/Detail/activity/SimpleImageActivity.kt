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

import net.dowhile.imageseeker.Constants
import net.dowhile.imageseeker.Detail.Fragments.ImageGalleryFragment
import net.dowhile.imageseeker.Detail.Fragments.ImageGridFragment
import net.dowhile.imageseeker.Detail.Fragments.ImageListFragment
import net.dowhile.imageseeker.Detail.Fragments.ImagePagerFragment
import net.dowhile.imageseeker.Model.ItemData
import net.dowhile.imageseeker.R

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
class SimpleImageActivity : FragmentActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val frIndex = intent.getIntExtra(Constants.Extra.FRAGMENT_INDEX, 0)
        var dataArray:Array<ItemData> = intent.getSerializableExtra("DATA")as Array<ItemData>
        var fr: Fragment?
        val tag: String
        val titleRes: Int
        when (frIndex) {
            ImageListFragment.INDEX -> {
                tag = ImageListFragment::class.java.simpleName
                fr = supportFragmentManager.findFragmentByTag(tag)
                if (fr == null) {
                    fr = ImageListFragment()
                }
                titleRes = R.string.ac_name_image_list
            }
            ImageGridFragment.INDEX -> {
                tag = ImageGridFragment::class.java.simpleName
                fr = supportFragmentManager.findFragmentByTag(tag)
                if (fr == null) {
                    fr = ImageGridFragment()
                }
                titleRes = R.string.ac_name_image_grid
            }
            ImagePagerFragment.INDEX -> {
                tag = ImagePagerFragment::class.java.simpleName
                fr = supportFragmentManager.findFragmentByTag(tag)
                if (fr == null) {
                    fr = ImagePagerFragment()
                    fr.arguments = intent.extras
                }
                titleRes = R.string.ac_name_image_pager
            }

            else -> {
                tag = ImageGalleryFragment::class.java.simpleName
                fr = supportFragmentManager.findFragmentByTag(tag)
                if (fr == null) {
                    fr = ImageGalleryFragment()
                }
                titleRes = R.string.ac_name_image_gallery
            }
        }

        setTitle("Simple")
        (fr as ImagePagerFragment).setData(dataArray)
        supportFragmentManager.beginTransaction().replace(android.R.id.content, fr, tag).commit()
    }
}