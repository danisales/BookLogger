package br.ufpe.cin.dso.booklogger.util

import br.ufpe.cin.dso.booklogger.model.Book
import com.github.kittinunf.fuel.httpGet
import org.json.JSONArray
import org.json.JSONObject

class GoogleBooksRequest {
    var TAG = "GoogleBooksRequest"
    var content = ""
    private fun request(url: String) : JSONObject {
        val (request, response, result) = url.httpGet().responseString()
        return JSONObject(result.get())
    }

    fun setReqContent(c: String) {
        this.content = c
    }

    fun search(query: String) : JSONObject {
        query.replace(" ", "+")
        val maxResults = 40
        return request("https://www.googleapis.com/books/v1/volumes?q=" + query + "&maxResults=$maxResults")
    }

    fun get(bookId: String) : JSONObject {
        return request("https://www.googleapis.com/books/v1/volumes/" + bookId)
    }

    fun results(query: String) : List<Book>{
        var result = search(query)
        var items = JSONArray()

        try {
            items = result.getJSONArray("items")
        } catch(e: Exception) {
            return arrayListOf<Book>()
        }

        var numItems = minOf(40, items.length())
        val listItems = ArrayList<Book>()

        (0..numItems-1).forEach { i ->
            var item = items.getJSONObject(i)

            var id = item.getString("id")
            var volumeInfo = item.getJSONObject("volumeInfo")
            var title = volumeInfo.getString("title")
            var author = ""
            try{
                author = volumeInfo.getJSONArray("authors").getString(0)
            }catch(e: Exception){
                author = "Autor nao informado"
            }
            var publisher = ""
            try{
                publisher = volumeInfo.getString("publisher")
            }catch(e: Exception){
                publisher = "Editora nao informada"
            }
            var imageLinks: JSONObject? = null
            var thumbnail: String? = null
            try{
                imageLinks = volumeInfo.getJSONObject("imageLinks")
                thumbnail = imageLinks.getString("smallThumbnail")
            } catch(e: Exception){}
            var borrowed = false
            var status = null

            var book = Book(id, title, author, publisher, thumbnail, borrowed, status)
            listItems.add(book)
        }
        return listItems
    }
}