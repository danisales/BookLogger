package br.ufpe.cin.dso.booklogger

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_search_book.*

class SearchBookActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_book)

        list_search_books.layoutManager = LinearLayoutManager(this)
        list_search_books.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        btn_search.setOnClickListener{
            var query = txt_search?.text.toString()
            if(query != null) {
                Thread() {

                    var items = GoogleBooksRequest().results(query)

                    runOnUiThread{
                        if(items.isEmpty()){
                            Toast.makeText(this@SearchBookActivity, "Nada foi encontrado",
                                    Toast.LENGTH_SHORT).show()
                        }
                        list_search_books.adapter = BookAdapter(items, this)
                    }


                }.start()
            }
        }
    }
}
