package br.ufpe.cin.dso.booklogger

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_reading.*
import kotlinx.android.synthetic.main.fragment_reading.view.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.runOnUiThread
import org.jetbrains.anko.support.v4.uiThread

class ReadingFragment : Fragment() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_reading, container, false)

        var list_books = view.findViewById(R.id.list_reading_books) as RecyclerView
        list_books.layoutManager = LinearLayoutManager(this.activity)
        list_books.addItemDecoration(DividerItemDecoration(this.activity, LinearLayoutManager.VERTICAL))

        Thread(){
            var items = GoogleBooksRequest().results("harry potter")
            this.requireActivity().runOnUiThread {
                list_books.adapter = BookAdapter(items, this.requireActivity())
            }
        }.start()

        view.btn_reading_add.setOnClickListener {
            requireActivity().startActivity(Intent(activity, SearchBookActivity::class.java))
        }

        return view
    }

    companion object {
        fun newInstance(): ReadingFragment = ReadingFragment()
    }
}
