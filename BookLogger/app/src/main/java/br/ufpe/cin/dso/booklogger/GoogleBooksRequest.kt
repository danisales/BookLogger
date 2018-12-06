package br.ufpe.cin.dso.booklogger

import android.util.Log
import com.github.kittinunf.fuel.httpGet
import org.json.JSONObject

class GoogleBooksRequest {
    var TAG = "GoogleBooksRequest"
    var content = ""
    private fun request(url: String) : JSONObject {
        val (request, response, result) = url.httpGet().responseString()

        /*var obj = JSONObject(result.get())
        var kind = obj.getString("kind")
        Log.d(TAG, kind)*/

        return JSONObject(result.get())
    }

    fun setReqContent(c: String) {
        this.content = c
    }

    fun search(query: String) : JSONObject {
        query.replace(" ", "+")
        return request("https://www.googleapis.com/books/v1/volumes?q=" + query)
    }

    fun get(bookId: String) : JSONObject {
        return request("https://www.googleapis.com/books/v1/volumes/" + bookId)
    }
}