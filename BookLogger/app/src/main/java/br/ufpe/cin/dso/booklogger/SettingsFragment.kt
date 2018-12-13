package br.ufpe.cin.dso.booklogger


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_settings.*


class SettingsFragment : Fragment() {
    private var mAuth = FirebaseAuth.getInstance()
    private var user = mAuth.currentUser

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_settings, container, false)

        var txt_email = view.findViewById<EditText>(R.id.txt_settings_email)
        txt_email.isEnabled = false
        txt_email.setText(user!!.email)

        var btn_signout = view.findViewById<Button>(R.id.btn_logout)
        btn_signout.setOnClickListener {
            signOut()
        }
        
        return view
    }

    fun signOut(){
        mAuth.signOut()
        this.requireActivity().startActivity(Intent(activity, LoginActivity::class.java))
    }
}
