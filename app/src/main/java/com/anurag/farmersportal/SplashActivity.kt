package com.anurag.farmersportal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.TextView

class SplashActivity : AppCompatActivity() {
    lateinit var imglogo: ImageView
    lateinit var textfamers: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        imglogo = findViewById(R.id.imgfarmerslogo)
        textfamers = findViewById(R.id.txtfarmersapp)
        Handler().postDelayed({
            val intent = Intent(this@SplashActivity,LoginActivity::class.java)
            startActivity(intent)
        }, 1000)
    }
}
