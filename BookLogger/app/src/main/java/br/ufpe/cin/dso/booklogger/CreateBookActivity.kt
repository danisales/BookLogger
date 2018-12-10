package br.ufpe.cin.dso.booklogger

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_create_book.*

class CreateBookActivity : AppCompatActivity() {
    val TAG = "CreateBookActivity"
    private var mAuth = FirebaseAuth.getInstance()
    private var user = mAuth.currentUser
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_book)
        database = FirebaseDatabase.getInstance().reference

        txt_book_title.isEnabled = false
        txt_book_author.isEnabled = false
        txt_book_publisher.isEnabled = false

        txt_book_title.setText(intent.extras.getString("TITLE"))
        txt_book_author.setText(intent.extras.getString("AUTHOR"))
        txt_book_publisher.setText(intent.extras.getString("PUBLISHER"))

        val adapter = ArrayAdapter.createFromResource(this, R.array.book_status,
                android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_books.adapter = adapter

        spinner_books.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                switch_borrowed.isChecked = false
                switch_borrowed.isEnabled = !spinner_books.selectedItem
                        .equals(resources.getStringArray(R.array.book_status)[3])
            }
        }

        btn_add_book.setOnClickListener{
            Log.d(TAG, spinner_books.selectedItem.toString())
            try{
                addBook(user!!.uid, getBook())
                startActivity(Intent(this.applicationContext, MainActivity::class.java))
            } catch(e: Exception){
                Log.w(TAG, e.message)
            }
        }
    }

    private fun getBook() : Book{
        val id = intent.extras.getString("ID")
        val title = intent.extras.getString("TITLE")
        val author = intent.extras.getString("AUTHOR")
        val publisher = intent.extras.getString("PUBLISHER")
        val thumbnail = intent.extras.getString("THUMBNAIL")
        val borrowed = switch_borrowed.isChecked
        val status = spinner_books.selectedItem.toString()

        return Book(id, title, author, publisher, thumbnail, borrowed, status)
    }

    private fun addBook(userId: String, book: Book){
        try{
            database.child("users")
                    .child(userId)
                    .child("books")
                    .child(book.id)
                    .setValue(book)
            Toast.makeText(this, "Livro adicionado com sucesso", Toast.LENGTH_SHORT).show()
            //finish()
        } catch(e: Exception){
            Toast.makeText(this, "Algo deu errado", Toast.LENGTH_SHORT).show()
            Log.w(TAG, e.message)
        }
    }
}
