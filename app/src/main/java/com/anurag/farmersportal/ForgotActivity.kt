package com.anurag.farmersportal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.*

class ForgotActivity : AppCompatActivity() {
    lateinit var forgotemail:EditText
    lateinit var frgtaadharnumber:EditText
    lateinit var frgtnickname:EditText
    lateinit var frgtusertype:AutoCompleteTextView
    lateinit var verify: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)

        frgtusertype=findViewById(R.id.frgtautoComplete)
        forgotemail=findViewById(R.id.etforgotemail)
        frgtaadharnumber=findViewById(R.id.etforgotaadharnumber)
        frgtnickname=findViewById(R.id.etnickname)
        verify=findViewById(R.id.btnverify)

        val user=resources.getStringArray(R.array.usertype)
        val array= ArrayAdapter(this,R.layout.dropdown_item,user)
        val autocompletetv=findViewById<AutoCompleteTextView>(R.id.frgtautoComplete)
        autocompletetv.setAdapter(array)

        var helper=MyDBHelper(applicationContext)
        var db=helper.readableDatabase

        verify.setOnClickListener {
            var loginut1="BUYER"
            var buyorpro2=frgtusertype.text.toString()
            var frgtemail=forgotemail.text.toString()
            var args= listOf<String>(frgtemail,frgtaadharnumber.text.toString(),frgtnickname.text.toString()).toTypedArray()
            var rs=db.rawQuery("SELECT * FROM BUYERUSERS WHERE EMAIL=? AND USERAADHAR=? AND NICKNAME=?",args)
            var rs2=db.rawQuery("SELECT * FROM PRODUCERUSERS WHERE EMAIL=? AND USERAADHAR=? AND NICKNAME=?",args)
            if(loginut1.equals(buyorpro2)) {
                if (rs.moveToNext()) {
                    Toast.makeText(this@ForgotActivity, "Verified Successfully!!", Toast.LENGTH_SHORT)
                        .show()
                    val intent = Intent(this@ForgotActivity, ChangePasswordActivity::class.java)
                    intent.putExtra("USERTYPE",buyorpro2)
                    intent.putExtra("EMAIL",frgtemail)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@ForgotActivity, "Incorrect Buyer's Credentials", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            else{
                if (rs2.moveToNext()) {
                    Toast.makeText(this@ForgotActivity, "Verified Successfully!!", Toast.LENGTH_SHORT)
                        .show()
                    val intent = Intent(this@ForgotActivity, ChangePasswordActivity::class.java)
                    intent.putExtra("USERTYPE",buyorpro2)
                    intent.putExtra("EMAIL",frgtemail)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@ForgotActivity, "Incorrect Producer's Credentials", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            finishAffinity()
            System.exit(0)
            return
        }

        this.doubleBackToExitPressedOnce = true
        val intent = Intent(this@ForgotActivity, LoginActivity::class.java)
        startActivity(intent)

        Handler(Looper.getMainLooper()).postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }

}