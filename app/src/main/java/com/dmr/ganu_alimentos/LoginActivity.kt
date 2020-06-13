package com.dmr.ganu_alimentos

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signin.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        val animDrawable = this.login_layout.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(10)
        animDrawable.setExitFadeDuration(1500)
        animDrawable.start()


        registerButtonLogIn.setOnClickListener{
            startActivity(Intent(this, SigninActivity::class.java ))
            finish()
        }

        buttonLogIn.setOnClickListener{
            doLogin()
        }

    }

    fun doLogin(){
        if(emailLogIn.text.toString().isEmpty()){
            emailLogIn.error = "Please enter email"
            emailLogIn.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailLogIn.text.toString()).matches()){
            emailLogIn.error = "Please enter valid email"
            emailLogIn.requestFocus()
            return
        }

        if(passwordLogIn.text.toString().isEmpty()){
            passwordLogIn.error = "Please enter email"
            passwordLogIn.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(emailLogIn.text.toString(), passwordLogIn.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Toast.makeText(baseContext, task.exception?.localizedMessage,
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }

            }

    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser : FirebaseUser?){
        if(currentUser != null){
            startActivity(Intent(this, MainScreenActivity::class.java))
        }else{
            Toast.makeText(baseContext, "Login failed.",
                Toast.LENGTH_SHORT).show()
        }
    }


}
