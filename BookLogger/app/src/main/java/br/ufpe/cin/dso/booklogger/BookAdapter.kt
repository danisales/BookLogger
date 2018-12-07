package br.ufpe.cin.dso.booklogger

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_book.view.*


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

        holder?.itemView.setOnClickListener(){}
    }

    class ViewHolder(item: View): RecyclerView.ViewHolder(item), View.OnClickListener{
        val title = item.item_title
        val author = item.item_author

        override fun onClick(v: View?) {}
    }
}
