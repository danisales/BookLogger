package br.ufpe.cin.dso.booklogger

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_create_book.*

class CreateBookActivity : AppCompatActivity() {
    val TAG = "CreateBookActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_book)

        txt_book_title.isEnabled = false
        txt_book_author.isEnabled = false

        txt_book_title.setText(intent.extras.getString("TITLE"))
        txt_book_author.setText(intent.extras.getString("AUTHOR"))

        val adapter = ArrayAdapter.createFromResource(this, R.array.book_status,
                android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_books.adapter = adapter

        spinner_books.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                switch_borrowed.isEnabled = !spinner_books.selectedItem
                        .equals(resources.getStringArray(R.array.book_status)[3])
            }
        }

        btn_add_book.setOnClickListener{
            Log.d(TAG, spinner_books.selectedItem.toString())
        }

    }
}
