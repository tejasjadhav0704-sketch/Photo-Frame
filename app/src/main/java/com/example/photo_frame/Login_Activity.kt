package com.example.photo_frame

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.addCallback
import com.example.photo_frame.Register_Activity.tj
import com.example.photo_frame.databinding.ActivityLoginBinding

class Login_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.textView.setOnClickListener {
            startActivity(Intent(this, Register_Activity::class.java))
            finish()
        }
        binding.textView5.setOnClickListener {
            startActivity(Intent(this, Register_Activity::class.java))
            finish()
        }

        binding.button1.setOnClickListener {
            val userN = binding.editText1.text.toString().trim()
            val passW = binding.editText2.text.toString().trim()

            if (userN.isEmpty() || passW.isEmpty()) {
                if (userN.isEmpty()) binding.editText1.error = "Enter Email"
                if (passW.isEmpty()) binding.editText2.error = "Enter Password"
            } else {
                tj.auth.signInWithEmailAndPassword(userN, passW).addOnSuccessListener {
                    Toast.makeText(this, "User Logged In Successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                    binding.editText1.setText("")
                    binding.editText2.setText("")
                }.addOnFailureListener {
                    Toast.makeText(this, "Login Failed: ${it.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        }
    }
