package br.ufpe.cin.dso.booklogger

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_book_info.*

class BookInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_info)

        var id = intent.extras.getString("ID")

        var title = intent.extras.getString("TITLE")
        txt_book_info_title.text = title

        var author = intent.extras.getString("AUTHOR")
        txt_book_info_author.text = author

        var publisher = intent.extras.getString("PUBLISHER")
        txt_book_info_publisher.text = publisher

        var thumbnail = intent.extras.getString("THUMBNAIL")

        var status = intent.extras.getString("STATUS")
        txt_book_info_status.text = status

        var isBorrowed = if(intent.getBooleanExtra("BORROWED", false)) "Sim" else "Não"
        txt_book_borrowed.text = "Emprestado: " + isBorrowed

        if(!status.equals("Wishlist")){
            btn_info_price.visibility = View.GONE
        } else {
            txt_book_borrowed.visibility = View.GONE
        }

        btn_info_price.setOnClickListener {
            openLink(title, author)
        }

        btn_info_edit_book.setOnClickListener{
            var borrowed = intent.getBooleanExtra("BORROWED", false)
            startEditActivity(id, title, author, publisher, thumbnail, status, borrowed)
        }
    }

    private fun openLink(title: String, author: String){
        var query = title + " " + author
        var url = "https://www.zoom.com.br/livros?q=" + query
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    private fun startEditActivity(id: String, title: String, author: String,
                                  publisher: String, thumbnail: String, status: String,
                                  borrowed: Boolean){
        var intent = Intent(this, CreateBookActivity::class.java)
        intent.putExtra("MODE", "EDIT")
        intent.putExtra("ID", id)
        intent.putExtra("TITLE", title)
        intent.putExtra("AUTHOR", author)
        intent.putExtra("PUBLISHER", publisher)
        intent.putExtra("THUMBNAIL", thumbnail)
        intent.putExtra("STATUS", status)
        intent.putExtra("BORROWED", borrowed)
        startActivity(intent)
    }
}
