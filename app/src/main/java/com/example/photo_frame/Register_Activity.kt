package com.example.photo_frame

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.photo_frame.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Register_Activity : AppCompatActivity() {

    companion object tj{
        var database = FirebaseFirestore.getInstance()
        var auth = FirebaseAuth.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        onBackPressedDispatcher.addCallback(this){
            startActivity(Intent(this@Register_Activity, Login_Activity::class.java))
            finish()
        }

        val binding : ActivityRegisterBinding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.textView.setOnClickListener {
            startActivity(Intent(this, Login_Activity::class.java))
            finish()
        }
        binding.textView5.setOnClickListener {
            startActivity(Intent(this, Login_Activity::class.java))
            finish()
        }

        binding.button1.setOnClickListener {

            val userN = binding.editText1.text.toString()
            val passW = binding.editText2.text.toString()
            val confirmP = binding.editText3.text.toString()

            if(userN.isEmpty() && passW.isEmpty() && confirmP.isEmpty())
            {
                binding.editText1.error = "Enter Username"
                binding.editText2.error = "Enter Password"
                binding.editText3.error = "Enter Confirm Password"
            }
            else if(userN.isEmpty())
            {
                binding.editText1.error = "Enter Username"
            }
            else if(passW.isEmpty())
            {
                binding.editText2.error = "Enter Password"
            }
            else if(confirmP.isEmpty())
            {
                binding.editText3.error = "Enter Confirm Password"
            }
            else if(passW != confirmP)
            {
                binding.editText3.error = "Password not matched with previous"
            }
            else
            {
                if(passW.length < 6)
                {
                    binding.editText2.error = "Password length should be greater than 6"
                    return@setOnClickListener
                }
                val user = Users(userN,passW)

                auth.createUserWithEmailAndPassword(userN,passW).addOnSuccessListener {
                    Toast.makeText(this, "User Created Successfully", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }

                database.collection("Users")
                    .add(user)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Data added successfully", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,Login_Activity::class.java))
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}
