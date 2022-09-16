package com.fuathuriyetoglu.e_pano.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.fuathuriyetoglu.e_pano.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding .inflate(layoutInflater)
        val view =binding.root
        setContentView(view)
        auth = Firebase.auth
        val currentUser = auth.currentUser
        if (currentUser != null){
            val intent =Intent(this, Upload::class.java)
            startActivity(intent)
            finish()

        }

    }

   fun SignInClicked (view: View){
       val email =binding.editTextTextEmailAddress.text.toString()
       val password =binding.editTextNumberPassword.text.toString()
       if(email.equals("") || password.equals("")){
           Toast.makeText(this , "Enter email and password!", Toast.LENGTH_SHORT).show()
       }else{
           auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
               val intent =Intent(this, Upload::class.java)
               startActivity(intent)
               finish()


           }.addOnFailureListener {
               Toast.makeText(this@Login, it.localizedMessage,Toast.LENGTH_LONG).show()

           }
       }

   }


}