package net.dowhile.imageseeker.Network

import android.app.ProgressDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

import java.net.HttpURLConnection
import java.net.URL

import android.R.id.message
import android.app.Activity
import com.bumptech.glide.gifdecoder.GifHeaderParser.TAG
import java.io.*
import java.net.MalformedURLException

/**
 * Created by wz on 23/03/2017.
 */

class MyImageDownloader(var activity: Activity) {

    fun downLoad(url:String,load:(percent:Int)->Unit,complete:()->Unit):Unit{
        savePicture(getHttpBitmap(url,load),complete)
    }

    fun getHttpBitmap(url: String,load:(percent:Int)->Unit): Bitmap {
        var bitmap: Bitmap? = null
        try {
            val pictureUrl = URL(url)
            val `in` = pictureUrl.openStream()
            bitmap = BitmapFactory.decodeStream(`in`)
            `in`.close()
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return bitmap!!
    }

    fun savePicture(bitmap: Bitmap,callback:()->Unit) {
        val pictureName = "/mnt/sdcard/mzt/" + "car" + ".jpg"
        val file = File(pictureName)
        val out: FileOutputStream
        try {
            out = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
            out.flush()
            out.close()
            callback()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}
