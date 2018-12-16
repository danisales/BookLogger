package br.ufpe.cin.dso.booklogger.service

import android.app.IntentService
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import br.ufpe.cin.dso.booklogger.model.Book
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class FirebaseService : IntentService("FirebaseService") {
    private val TAG = "FirebaseService"
    private var mAuth = FirebaseAuth.getInstance()
    private var user = mAuth.currentUser
    private lateinit var database: DatabaseReference

    override fun onHandleIntent(intent: Intent?) {
        database = FirebaseDatabase.getInstance().reference

        Log.d(TAG, "Service running")

        var status = intent!!.extras.getString("STATUS")
        getItems(status)
    }

    fun getItems(status: String) {
        var context = this
        val booksListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var books: ArrayList<Book> = arrayListOf()
                dataSnapshot.children.forEach {
                    var book = it.getValue<Book>(Book::class.java)!!
                    if(book.status.equals(status)) books.add(book)
                }
                //books = books.filter{x -> x.status.equals(status)} as ArrayList<Book>

                var bundle = Bundle()
                bundle.putSerializable("RESULT", books)

                var broadcast = Intent("FirebaseBroadcast")
                broadcast.putExtras(bundle)
                var instance = LocalBroadcastManager.getInstance(context)
                        .sendBroadcast(broadcast)
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

}
