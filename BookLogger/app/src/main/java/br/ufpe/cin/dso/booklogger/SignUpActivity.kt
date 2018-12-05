package br.ufpe.cin.dso.booklogger

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    private val TAG = "SignUpActivity"
    private var mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btn_signup_submit.setOnClickListener {
            var email = txt_signup_email?.text.toString()
            var password = txt_signup_password?.text.toString()
            signUp(email, password)
        }
    }

    @SuppressLint("ShowToast")
    fun signUp(email:String, password: String){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful()) {
                        Log.d(TAG, "createUserWithEmail:success")
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show()
                    }
                }
    }
}
