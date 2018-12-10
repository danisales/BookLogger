package br.ufpe.cin.dso.booklogger

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_book.view.*
import org.jetbrains.anko.activityManager


class BookAdapter(private val items: List<Book>, private val c : Context)
    : RecyclerView.Adapter<BookAdapter.ViewHolder>()  {

    override fun getItemCount() : Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(c).inflate(R.layout.item_book, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val i = items.get(position)
        holder?.title?.text = i.title
        holder?.author?.text = i.author
        if(i.borrowed){
            holder?.borrowed?.text = "Emprestado"
            holder?.itemView.setBackgroundColor(Color.parseColor("#efeded"))
        }

        var activity = c as Activity

        if(!activity.localClassName.equals("MainActivity")) {
            holder?.itemView.setOnClickListener() {
                var intent = Intent(c, CreateBookActivity::class.java)
                intent.putExtra("ID", i.id)
                intent.putExtra("TITLE", i.title)
                intent.putExtra("AUTHOR", i.author)
                intent.putExtra("PUBLISHER", i.publisher)
                intent.putExtra("THUMBNAIL", i.thumbnail)
                c.startActivity(intent)
            }
        }
    }

    class ViewHolder(item: View): RecyclerView.ViewHolder(item), View.OnClickListener{
        val title = item.item_title
        val author = item.item_author
        val borrowed = item.item_borrowed

        override fun onClick(v: View?) {}
    }
}
