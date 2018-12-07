package br.ufpe.cin.dso.booklogger

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import android.util.Log
import android.widget.Toast
import org.jetbrains.anko.doAsync

class LoginActivity : AppCompatActivity() {
    private val TAG = "LoginActivity"
    private var mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login_initial.setOnClickListener {
            var email = txt_login_email?.text.toString()
            var password = txt_login_password?.text.toString()
            login(email, password)
        }
        btn_signup_initial.setOnClickListener { startActivity(Intent(applicationContext, SignUpActivity::class.java)) }
    }

    fun login(email: String, password: String){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if(task.isSuccessful) {
                        Log.d(TAG, "signInWithEmail:success")
                        startActivity(Intent(applicationContext, MainActivity::class.java))
                    } else {
                        Log.e(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(this@LoginActivity, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()

                    }
                }
    }
}
