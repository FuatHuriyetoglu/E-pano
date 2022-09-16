package com.fuathuriyetoglu.e_pano.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.fuathuriyetoglu.e_pano.R
import com.fuathuriyetoglu.e_pano.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        auth = Firebase.auth
        setContentView(R.layout.activity_main)

    }
    fun GoUtiyo (view : View){
val intent = Intent(this@MainActivity, UTIYO::class.java)
        startActivity(intent)
        finish()
    }
    fun GoMYO (view : View){
        val intent = Intent(this@MainActivity, MYO::class.java)
        startActivity(intent)
        finish()

    }



}