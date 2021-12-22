package com.anurag.farmersportal

import androidx.appcompat.app.AppCompatActivity
import com.gospelware.liquidbutton.LiquidButton
import android.widget.TextView
import android.os.Bundle
import com.anurag.farmersportal.R
import com.gospelware.liquidbutton.LiquidButton.PourFinishListener
import android.widget.Toast
import android.content.Intent
import android.view.View
import com.anurag.farmersportal.LoginActivity

internal class LiquidLoadingActivity : AppCompatActivity() {
    lateinit var liquidbutton: LiquidButton
    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.liquidloading)
        liquidbutton = findViewById<View>(R.id.liquidbutton) as LiquidButton
        textView = findViewById(R.id.textview)
        liquidbutton.startPour()
        liquidbutton!!.isFillAfter = true
        liquidbutton!!.isAutoPlay = true
        liquidbutton!!.setPourFinishListener(object : PourFinishListener {
            override fun onPourFinish() {
                Toast.makeText(
                    this@LiquidLoadingActivity,
                    "Registered Successfully!!",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this@LiquidLoadingActivity,LoginActivity::class.java)
                startActivity(intent)
            }

            override fun onProgressUpdate(progress: Float) {
                textView.setText(String.format("%.2f", progress * 100) + "%")
            }
        })
    }
}