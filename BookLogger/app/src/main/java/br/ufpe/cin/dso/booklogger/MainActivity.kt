package br.ufpe.cin.dso.booklogger

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_reading -> {
                message.setText(R.string.title_reading)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_read -> {
                message.setText(R.string.title_read)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_to_read -> {
                message.setText(R.string.title_to_read)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_wishlist -> {
                message.setText(R.string.title_wishlist)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                message.setText(R.string.title_settings)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
