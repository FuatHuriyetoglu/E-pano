package com.fuathuriyetoglu.e_pano.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.fuathuriyetoglu.e_pano.R
import com.fuathuriyetoglu.e_pano.adapter.FeedRecyclerAdapter
import com.fuathuriyetoglu.e_pano.databinding.ActivityMyoBinding
import com.fuathuriyetoglu.e_pano.databinding.ActivityUtiyoBinding
import com.fuathuriyetoglu.e_pano.model.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UTIYO : AppCompatActivity() {
    private lateinit var binding : ActivityUtiyoBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore
    private lateinit var postArrayList :ArrayList<Post>
    private lateinit var feedAdapter: FeedRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUtiyoBinding.inflate(layoutInflater)
        val view =binding.root
        auth = Firebase.auth
        db = Firebase.firestore
        postArrayList = ArrayList<Post>()
        getData()
        binding.recyclerview2.layoutManager = LinearLayoutManager(this)
        feedAdapter = FeedRecyclerAdapter(postArrayList)
        binding.recyclerview2.adapter = feedAdapter

        setContentView(view)
    }
    private fun getData(){
        db.collection("Documents").orderBy("date", Query.Direction.DESCENDING).addSnapshotListener{ value, error ->
            if(error != null){
                Toast.makeText(this,error.localizedMessage,Toast.LENGTH_LONG).show()
            }else{
                if(value != null){
                    if(!value.isEmpty) {
                        val documents = value.documents
                        postArrayList.clear()
                        for (document in documents){
                            //casting
                            val comment = document.get("comment") as String
                            val userEmail = document.get("userEmail") as String
                            val downloadUrl = document.get("downloadUrl") as String

                            val post= Post(userEmail ,comment , downloadUrl)
                            postArrayList.add(post)

                        }
                        feedAdapter.notifyDataSetChanged()
                    }
                }
            }

        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater =menuInflater
        menuInflater.inflate(R.menu.menu_epano,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId== R.id.add_document){
            Toast.makeText(getApplicationContext(), "Sadece akademisyenlere özeldir", Toast.LENGTH_LONG).show();
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }else if(item.itemId== R.id.signout){
            auth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }

        return super.onOptionsItemSelected(item)
    }
}