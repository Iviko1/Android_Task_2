package com.example.firebaseappbtu1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var forgotPasswordEmailEditText : EditText
    private lateinit var resetPasswordButton : Button
    private lateinit var backToLoginButton: TextView

    private val auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        init()
        listeners()
    }

    private fun listeners() {

        resetPasswordButton.setOnClickListener {

            val email = forgotPasswordEmailEditText.text.toString()

            if (email.isEmpty() || email.contains(" ") )
            {
                Toast.makeText(this, "Please Enter a Valid Email Address!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Toast.makeText(this, "Password Reset Email Sent!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }

        }

        backToLoginButton.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun init(){
        forgotPasswordEmailEditText = findViewById(R.id.forgotPasswordEmailEditText)
        resetPasswordButton = findViewById(R.id.resetPasswordButton)
        backToLoginButton = findViewById(R.id.backToLoginButton)
    }
}