package br.ufpe.cin.dso.booklogger.ui.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.ufpe.cin.dso.booklogger.model.Book
import br.ufpe.cin.dso.booklogger.ui.activity.BookInfoActivity
import br.ufpe.cin.dso.booklogger.ui.activity.CreateBookActivity
import br.ufpe.cin.dso.booklogger.R
import br.ufpe.cin.dso.booklogger.ui.activity.MainActivity
import com.bumptech.glide.Glide
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

        if(i.borrowed){
            holder?.borrowed?.text = "Emprestado"
            holder?.itemView.setBackgroundColor(Color.parseColor("#efeded"))
        }

        if(i.thumbnail != null && !i.thumbnail.equals("")){
            loadImage(holder, i.thumbnail)
        }

        holder?.itemView.setOnClickListener() {
            var activity = c as Activity

            var id = i.id
            var title = i.title
            var author = i.author
            var publisher = i.publisher
            var thumbnail = i.thumbnail!!
            var borrowed = i.borrowed
            var status = i.status

            var intent = createIntent(activity, BookInfoActivity(),
                    id, title, author, publisher, thumbnail, borrowed, status)
            if (!activity.localClassName.equals("ui.activity.MainActivity")) {
                intent = createIntent(activity, CreateBookActivity(),
                        id, title, author, publisher, thumbnail, borrowed, status)
                intent.putExtra("MODE", "CREATE")
            }
            c.startActivity(intent)
        }

    }

    class ViewHolder(item: View): RecyclerView.ViewHolder(item), View.OnClickListener{
        val title = item.item_title
        val author = item.item_author
        val borrowed = item.item_borrowed
        val thumbnail = item.item_thumbnail

        override fun onClick(v: View?) {}
    }

    private fun loadImage(holder: ViewHolder, url: String){
        Glide.with(c)
                .load(url)
                .centerCrop()
                .crossFade()
                .override(64, 96)
                .into(holder.thumbnail)
    }

    private fun createIntent (c: Context, a: Activity, id: String, title: String, author: String,
                              publisher: String, thumbnail: String, borrowed: Boolean, status: String?) : Intent{
        var intent = Intent(c, a::class.java)
        intent.putExtra("ID", id)
        intent.putExtra("TITLE", title)
        intent.putExtra("AUTHOR", author)
        intent.putExtra("PUBLISHER", publisher)
        intent.putExtra("THUMBNAIL", thumbnail)
        intent.putExtra("STATUS", status)
        intent.putExtra("BORROWED", borrowed)
        return intent
    }
}
