package com.dmr.ganu_alimentos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signin.*

class SigninActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        auth = FirebaseAuth.getInstance()

        buttonSignIn.setOnClickListener{
            signUpUser()
        }

        buttonLogInSignIn.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java ))
            finish()
        }
    }

    private fun signUpUser(){
        if(emailSignIn.text.toString().isEmpty()){
            emailSignIn.error = "Please enter email"
            emailSignIn.requestFocus()
            return
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailSignIn.text.toString()).matches()){
            emailSignIn.error = "Please enter valid email"
            emailSignIn.requestFocus()
            return
        }
        if(passwordSignIn.text.toString().isEmpty()){
            passwordSignIn.error = "Please enter password"
            passwordSignIn.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(emailSignIn.text.toString(), passwordSignIn.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(baseContext, "Password must be at least 6 characters long.\nYou must use a valid email.",
                        Toast.LENGTH_SHORT).show()
                }

            }

    }


}
