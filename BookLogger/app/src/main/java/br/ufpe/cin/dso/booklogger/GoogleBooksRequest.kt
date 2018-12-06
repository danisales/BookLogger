package br.ufpe.cin.dso.booklogger

import android.util.Log
import com.github.kittinunf.fuel.Fuel

class GoogleBooksRequest {
    var TAG = "GoogleBooksRequest"
    private fun request(url: String){
        Fuel.get(url).responseString { request, response, result ->
            result.fold({ d ->
                Log.d(TAG, d)
            }, { err ->
                Log.w(TAG, err)
            })
        }
    }

    fun search(query: String){
        query.replace(" ", "+")
        request("https://www.googleapis.com/books/v1/volumes?q=" + query)
    }

    fun get(bookId: String){
        request("https://www.googleapis.com/books/v1/volumes/" + bookId)
    }
}