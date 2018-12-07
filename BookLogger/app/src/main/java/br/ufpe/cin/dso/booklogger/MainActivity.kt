package br.ufpe.cin.dso.booklogger

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toolbar

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_reading -> {
                val readingFragment = ReadingFragment.newInstance()
                openFragment(readingFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_read -> {
                message.setText("")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_to_read -> {
                message.setText("")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_wishlist -> {
                message.setText("")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                message.setText("")
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

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onBackPressed() {}
}
