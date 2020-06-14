package com.dmr.ganu_alimentos

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_signin.*

class SigninActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)



        buttonLogInSignIn.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java ))
            finish()

        }

        buttonSignIn.setOnClickListener{
            signUpUser()
        }

        buttonLogInSignIn.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java ))
            finish()
        }
    }

    private fun signUpUser(){

        val email = emailSignIn.text.toString()
        val password = passwordSignIn.text.toString()
        val name = nombreSignIn.text.toString()

        when{
            TextUtils.isEmpty(email) -> Toast.makeText(this, "Email is required", Toast.LENGTH_LONG).show()
            TextUtils.isEmpty(password) -> Toast.makeText(this, "Password is required", Toast.LENGTH_LONG).show()
            TextUtils.isEmpty(name) -> Toast.makeText(this, "Name is required", Toast.LENGTH_LONG).show()


            else -> {

                val progressDialog = ProgressDialog(this@SigninActivity)
                progressDialog.setTitle("SignUp")
                progressDialog.setMessage("Please wait, this may take a while")
                progressDialog.setCanceledOnTouchOutside(false)
                progressDialog.show()

                val auth = FirebaseAuth.getInstance()

                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            saveUserInfo(email, password, name,  progressDialog)
                            //startActivity(Intent(this, LoginActivity::class.java))
                            //finish()
                        } else {

                            val message = task.exception!!.toString()
                            Toast.makeText(this, "Error $message", Toast.LENGTH_LONG).show()
                            auth.signOut()
                            progressDialog.dismiss()

                        }

                    }

            }
        }

    }

    private fun saveUserInfo(email: String, password: String, name: String, progressDialog: ProgressDialog) {
        val currenUserId = FirebaseAuth.getInstance().currentUser!!.uid
        val usersRef: DatabaseReference = FirebaseDatabase.getInstance().reference.child("Users")

        val userMap = HashMap<String, Any>()
        userMap["uid"] = currenUserId
        userMap["email"] = email
        userMap["name"] = name
        userMap["password"] = password

        usersRef.child(currenUserId).setValue(userMap)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    progressDialog.dismiss()
                    Toast.makeText(this, "Account has been created", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@SigninActivity, MainScreenActivity::class.java  )
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                }else{
                    val message = task.exception!!.toString()
                    Toast.makeText(this, "Error $message", Toast.LENGTH_LONG).show()
                    FirebaseAuth.getInstance().signOut()
                    progressDialog.dismiss()
                }
            }

    }


}
