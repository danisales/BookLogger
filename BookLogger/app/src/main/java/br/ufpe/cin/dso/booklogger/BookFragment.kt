package br.ufpe.cin.dso.booklogger

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
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_book.*
import kotlinx.android.synthetic.main.fragment_book.view.*
import org.jetbrains.anko.support.v4.startService

class BookFragment : Fragment() {
    val TAG = "BookFragment"
    private var mAuth = FirebaseAuth.getInstance()
    private lateinit var database: DatabaseReference

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_book, container, false)
        database = FirebaseDatabase.getInstance().reference

        LocalBroadcastManager
                .getInstance(this.requireActivity())
                .registerReceiver(resultReceiver, IntentFilter("FirebaseBroadcast"))

        var service = Intent(activity, FirebaseService::class.java)
        var status = arguments!!.get("STATUS") as String
        service.putExtra("STATUS", status)
        activity!!.startService(service)

        view.btn_reading_add.setOnClickListener {
            requireActivity().startActivity(Intent(activity, SearchBookActivity::class.java))
        }

        return view
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager
                .getInstance(this.requireActivity())
                .unregisterReceiver(resultReceiver)
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

