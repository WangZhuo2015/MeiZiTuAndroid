//
//	ItemData.java
//  on 8/9/2016
//	Copyright © 2016. All rights reserved.
//	Model file generated using JSONExport: https://github.com/Ahmed-Ali/JSONExport
package net.dowhile.imageseeker.Model
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName


class ItemData
/**
 * Instantiate the instance using the passed jsonObject to set the properties values
 */
(json: String?) {

    @SerializedName("img")
    var img: String? = null
    @SerializedName("title")
    var title: String? = null

    init {
        val gson = Gson()
        if (json == null) { }
        else{
            val data = gson.fromJson(json,this.javaClass)
            title = data.title
            img = data.img
        }
    }
}