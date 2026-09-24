package com.example.a3tie_baydellamobile.pertemuan_4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a3tie_baydellamobile.MainActivity
import com.example.a3tie_baydellamobile.R
import com.example.a3tie_baydellamobile.databinding.ActivityFourthBinding

class fourthActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFourthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFourthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

            val name = intent.getStringExtra("name")
            val from = intent.getStringExtra("from")
            val age = intent.getIntExtra("age",0)
            Log.e("Data Intent","Nama: $name , Usia: $age, Asal: $from")

        binding.btnKembali.setOnClickListener {
            val i = Intent(this@fourthActivity, MainActivity::class.java)
            startActivity(i)
        }
    }
}