package br.ufpe.cin.dso.booklogger.ui.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import br.ufpe.cin.dso.booklogger.R
import br.ufpe.cin.dso.booklogger.ui.fragment.BookFragment
import br.ufpe.cin.dso.booklogger.ui.fragment.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var statusList : Array<String> = emptyArray()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_reading -> {
                val readingFragment = BookFragment.newInstance(statusList[0])
                openFragment(readingFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_read -> {
                val readFragment = BookFragment.newInstance(statusList[1])
                openFragment(readFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_to_read -> {
                val toReadFragment = BookFragment.newInstance(statusList[2])
                openFragment(toReadFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_wishlist -> {
                val wishlistFragment = BookFragment.newInstance(statusList[3])
                openFragment(wishlistFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                openFragment(SettingsFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        statusList = resources.getStringArray(R.array.book_status)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        openFragment(BookFragment.newInstance(statusList[0]))
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onBackPressed() {}
}
