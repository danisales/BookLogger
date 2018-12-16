package br.ufpe.cin.dso.booklogger.ui.fragment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.ufpe.cin.dso.booklogger.model.Book
import br.ufpe.cin.dso.booklogger.service.FirebaseService
import br.ufpe.cin.dso.booklogger.R
import br.ufpe.cin.dso.booklogger.ui.activity.SearchBookActivity
import br.ufpe.cin.dso.booklogger.ui.adapter.BookAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_book.*
import kotlinx.android.synthetic.main.fragment_book.view.*

class BookFragment : Fragment() {
    val TAG = "BookFragment"
    private var mAuth = FirebaseAuth.getInstance()
    private lateinit var database: DatabaseReference
    private var bookView: View? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.bookView = inflater.inflate(R.layout.fragment_book, container, false)
        database = FirebaseDatabase.getInstance().reference

        bookView!!.btn_reading_add.setOnClickListener {
            requireActivity().startActivity(Intent(activity, SearchBookActivity::class.java))
        }

        return bookView
    }

    override fun onStart() {
        super.onStart()

        LocalBroadcastManager
                .getInstance(this.requireActivity())
                .registerReceiver(resultReceiver, IntentFilter("FirebaseBroadcast"))

        var service = Intent(activity, FirebaseService::class.java)
        var status = arguments!!.get("STATUS") as String
        service.putExtra("STATUS", status)
        activity!!.startService(service)
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager
                .getInstance(this.requireActivity())
                .unregisterReceiver(resultReceiver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.bookView = null
    }

    private fun setRecyclerView(books: List<Book>){
        list_reading_books.layoutManager = LinearLayoutManager(this.requireActivity())
        list_reading_books.addItemDecoration(DividerItemDecoration(this.requireActivity(), LinearLayoutManager.VERTICAL))
        list_reading_books.adapter = BookAdapter(books, this.requireActivity())
    }


    private val resultReceiver = object : BroadcastReceiver() {
        @RequiresApi(Build.VERSION_CODES.M)
        override fun onReceive(context: Context, intent: Intent) {
            var bundle = intent.extras
            var items = bundle.getSerializable("RESULT") as List<Book>
            setRecyclerView(items)
        }
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

