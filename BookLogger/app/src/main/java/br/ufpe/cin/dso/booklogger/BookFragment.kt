package br.ufpe.cin.dso.booklogger

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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_reading.view.*

class BookFragment : Fragment() {
    val TAG = "BookFragment"
    private var mAuth = FirebaseAuth.getInstance()
    private var user = mAuth.currentUser
    private lateinit var database: DatabaseReference

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_reading, container, false)
        database = FirebaseDatabase.getInstance().reference

        Thread(){
            var status = arguments!!.get("STATUS") as String
            this.requireActivity().runOnUiThread {
                getItems(status)
            }
        }.start()

        view.btn_reading_add.setOnClickListener {
            requireActivity().startActivity(Intent(activity, SearchBookActivity::class.java))
        }

        return view
    }

    fun getItems(status: String) {
        var reqActivity = this.requireActivity()
        val booksListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var books: ArrayList<Book> = arrayListOf()
                dataSnapshot.children.forEach {
                    var book = it.getValue<Book>(Book::class.java)!!
                    books.add(book)
                }
                books = books.filter{x -> x.status.equals(status)} as ArrayList<Book>

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
        fun newInstance(status: String): BookFragment {
            val fragment = BookFragment()
            val args = Bundle()
            args.putString("STATUS", status)
            fragment.arguments = args
            return fragment
        }
    }

}

