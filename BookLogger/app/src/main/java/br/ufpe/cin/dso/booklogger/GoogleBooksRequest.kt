package br.ufpe.cin.dso.booklogger

import android.util.Log
import com.github.kittinunf.fuel.httpGet
import org.json.JSONObject

class GoogleBooksRequest {
    var TAG = "GoogleBooksRequest"
    var content = ""
    private fun request(url: String) : JSONObject {
        val (request, response, result) = url.httpGet().responseString()

        //var obj = JSONObject(result.get())
        //var volumeInfo = obj.getJSONObject("volumeInfo")
        //Log.d(TAG, volumeInfo.getString("title"))

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

    fun results(query: String) : List<Book>{
        var result = search(query)
        var items = result.getJSONArray("items")
        var numItems = 10
        val listItems = ArrayList<Book>()

        (0..numItems-1).forEach { i ->
            var item = items.getJSONObject(i)

            var id = item.getString("id")
            var volumeInfo = item.getJSONObject("volumeInfo")
            var title = volumeInfo.getString("title")
            var author = volumeInfo.getJSONArray("authors").getString(0)
            var imageLinks: JSONObject? = null
            var thumbnail: String? = null
            try{
                imageLinks = volumeInfo.getJSONObject("imageLinks")
                thumbnail = imageLinks.getString("smallThumbnail")
            } catch(e: Exception){}
            var borrowed = false
            var status = null

            var book = Book(id, title, author, thumbnail, borrowed, status)
            listItems.add(book)
        }
        return listItems
    }
}