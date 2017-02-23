package net.dowhile.imageseeker.Network

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.result.Result
import net.dowhile.imageseeker.Model.DetailAPIBase
import net.dowhile.imageseeker.Model.ItemData
import net.dowhile.imageseeker.Model.ListAPIBase
import net.dowhile.imageseeker.Model.ListData

/**
 * Created by wz on 16/9/8.
 */
class ServiceProxy {
    internal enum class Source {
        meizitu
    }
    companion object {
        private var source: Source = Source.meizitu
        internal fun setSource(mySource:Source) {
            source = mySource
        }

        private fun serviceEndPoint(): String {
            return "http://wzhere.cn:5000/"
        }


        private fun getListURL():String {
            when (source){
                Source.meizitu->{
                    return serviceEndPoint() + "list"
                }
            }
            return serviceEndPoint() + "list"
        }

        private fun getGirlURL(): String {
            when (source){
                Source.meizitu->{
                    return serviceEndPoint() + "girl"
                }
            }
            return serviceEndPoint() + "girl"
        }


        //    接口地址 :http://115.159.216.101:5000/list
        //    请求方法 :GET
        //    请求参数(url) :page
        internal fun getList (
        page:Int,
        complete:(list: Array<ListData>?, error: FuelError?) -> Unit){
            //an extension over string (support GET, PUT, POST, DELETE with httpGet(), httpPut(), httpPost(), httpDelete())
            FuelManager.instance
                    .request(Method.GET,
                            getListURL(),
                            listOf(Pair("page",page)))
                    .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        complete(null,result.error)
                    }
                    is Result.Success -> {
                        complete(ListAPIBase(result.value)
                                .listData
                                ,null)
                    }
                }
            }
        }


        //    接口地址 :http://115.159.216.101:5000/list
        //    请求方法 :GET
        //    请求参数(url) :page
        internal fun getImages (
                url:String,
                complete:(list: Array<ItemData>?, error: FuelError?) -> Unit){
            //an extension over string (support GET, PUT, POST, DELETE with httpGet(), httpPut(), httpPost(), httpDelete())
            FuelManager.instance
                    .request(Method.GET,
                            getGirlURL(),
                            listOf(Pair("url",url)))
                    .responseString { request, response, result ->
                        print(request.request)
                        when (result) {
                            is Result.Failure -> {
                                complete(null,result.error)
                            }
                            is Result.Success -> {
                                complete(DetailAPIBase(result.value).data,null)
                            }
                        }
                    }
        }
    }//static
}