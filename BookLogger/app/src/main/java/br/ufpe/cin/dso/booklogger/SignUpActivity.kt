package br.ufpe.cin.dso.booklogger

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    private val TAG = "SignUpActivity"
    private var mAuth = FirebaseAuth.getInstance()
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        database = FirebaseDatabase.getInstance().reference

        btn_signup_submit.setOnClickListener {
            var email = txt_signup_email?.text.toString()
            var password = txt_signup_password?.text.toString()
            signUp(email, password)
        }
    }

    @SuppressLint("ShowToast")
    private fun signUp(email:String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful()) {
                        Log.d(TAG, "createUserWithEmail:success")
                        onAuthSuccess(task.result.user)
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(this, "Algo deu errado", Toast.LENGTH_SHORT).show()
                    }
                }
    }

    private fun onAuthSuccess(user: FirebaseUser){
        var id = user.uid
        var email = user.email
        try {
            database.child("users").child(id).child("email").setValue(email)
            Toast.makeText(this, "Cadastro feito com sucesso", Toast.LENGTH_SHORT).show()
            finish()
        } catch(e: Exception){
            Toast.makeText(this, "Algo deu errado", Toast.LENGTH_SHORT).show()
            Log.d(TAG, e.message)
        }
    }
}
