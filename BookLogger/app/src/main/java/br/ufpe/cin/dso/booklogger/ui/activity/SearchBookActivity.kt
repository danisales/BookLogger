package br.ufpe.cin.dso.booklogger.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_search_book.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.content.LocalBroadcastManager
import br.ufpe.cin.dso.booklogger.model.Book
import br.ufpe.cin.dso.booklogger.service.GoogleBooksService
import br.ufpe.cin.dso.booklogger.R
import br.ufpe.cin.dso.booklogger.ui.adapter.BookAdapter


class SearchBookActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_book)

        LocalBroadcastManager
                .getInstance(this)
                .registerReceiver(resultReceiver, IntentFilter("GoogleBooksBroadcast"))

        btn_search.setOnClickListener{
            var service = Intent(this, GoogleBooksService::class.java)
            var query = txt_search?.text.toString()
            service.putExtra("QUERY", query)

            if(query != null) {
                startService(service)
            }
        }
    }

    private fun setRecyclerView(books: List<Book>){
        list_search_books.layoutManager = LinearLayoutManager(this)
        list_search_books.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        list_search_books.adapter = BookAdapter(books, this)
    }

    private val resultReceiver = object : BroadcastReceiver() {
        @RequiresApi(Build.VERSION_CODES.M)
        override fun onReceive(context: Context, intent: Intent) {
            var bundle = intent.extras
            var items = bundle.getSerializable("RESULT") as List<Book>

            setRecyclerView(items)
        }
    }

}
