package br.ufpe.cin.dso.booklogger

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_reading.*
import kotlinx.android.synthetic.main.fragment_reading.view.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.runOnUiThread
import org.jetbrains.anko.support.v4.uiThread

class ReadingFragment : Fragment() {
    val TAG = "ReadingFragment"
    private var mAuth = FirebaseAuth.getInstance()
    private var user = mAuth.currentUser
    private lateinit var database: DatabaseReference
    val books: MutableList<Book> = mutableListOf()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_reading, container, false)
        database = FirebaseDatabase.getInstance().reference

        Thread(){
            this.requireActivity().runOnUiThread {
                getItems()
            }
        }.start()

        view.btn_reading_add.setOnClickListener {
            requireActivity().startActivity(Intent(activity, SearchBookActivity::class.java))
        }

        return view
    }

    fun getItems() {
        var reqActivity = this.requireActivity()
        val booksListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.d(TAG, dataSnapshot.childrenCount.toString())
                var books: MutableList<Book> = mutableListOf()
                dataSnapshot.children.forEach {
                    var book = it.getValue<Book>(Book::class.java)!!
                    books.add(book)
                }

                var listBooks = view!!.findViewById(R.id.list_reading_books) as RecyclerView
                listBooks.layoutManager = LinearLayoutManager(reqActivity)
                listBooks.addItemDecoration(DividerItemDecoration(reqActivity, LinearLayoutManager.VERTICAL))
                listBooks.adapter = BookAdapter(books, reqActivity)
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Log.w(TAG, databaseError.toException().message)
            }
        }
        database.child("users")
                .child(user!!.uid)
                .child("books")
                .addListenerForSingleValueEvent(booksListener)
    }

    companion object {
        fun newInstance(): ReadingFragment = ReadingFragment()
    }

}

