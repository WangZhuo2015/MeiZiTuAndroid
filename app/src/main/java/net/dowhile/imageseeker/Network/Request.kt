package net.dowhile.imageseeker.Network

import android.util.Log
import net.dowhile.imageseeker.Model.ListAPIBase
import net.dowhile.imageseeker.Model.ListData
import org.json.JSONObject
import java.net.URL

/**
 * Created by wz on 16/9/7.
 */
public class Request(val url:String, val callback: (strData:String)->Unit) {
    public fun run(){
        val listStr = URL(url).readText()
        callback(listStr)
    }
}