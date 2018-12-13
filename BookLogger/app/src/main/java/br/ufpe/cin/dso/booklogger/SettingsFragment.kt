package br.ufpe.cin.dso.booklogger


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_settings.*


class SettingsFragment : Fragment() {
    private var mAuth = FirebaseAuth.getInstance()
    private var user = mAuth.currentUser

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_settings, container, false)

        var userEmail = user!!.email

        var txt_email = view.findViewById<EditText>(R.id.txt_settings_email)
        txt_email.isEnabled = false
        txt_email.setText(userEmail)

        var btn_update_password = view.findViewById<Button>(R.id.btn_update_password)
        btn_update_password.setOnClickListener{
            var currentPassword = txt_current_password?.text.toString()
            var newPassword = txt_new_password?.text.toString()
            var confirmNewPassword = txt_repeat_new_password?.text.toString()

            if(newPassword.equals(confirmNewPassword)){
                authAndUpdatePassword(userEmail!!, currentPassword, newPassword)
            } else {
                Toast.makeText(context, "Algo deu errado, verifique seus dados",
                        Toast.LENGTH_SHORT).show()
            }
        }

        var btn_signout = view.findViewById<Button>(R.id.btn_logout)
        btn_signout.setOnClickListener {
            signOut()
        }

        return view
    }

    fun authAndUpdatePassword(email: String, currentPassword: String, newPassword: String){
        mAuth.signInWithEmailAndPassword(email, currentPassword)
                .addOnCompleteListener(this.requireActivity()) { task ->
                    if(task.isSuccessful) {
                        updatePassword(newPassword)
                    } else {
                        Toast.makeText(context, "Algo deu errado, verifique seus dados",
                                Toast.LENGTH_SHORT).show()
                    }
                }
    }

    fun updatePassword(password: String){
        user!!.updatePassword(password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(context, "Senha alterada com sucesso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Não foi possível alterar sua senha", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun signOut(){
        mAuth.signOut()
        this.requireActivity().startActivity(Intent(activity, LoginActivity::class.java))
    }
}
