package com.anurag.farmersportal

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.*

open class LoginActivity : AppCompatActivity() {
    lateinit var signup: TextView
    lateinit var button: Button
    lateinit var loginusertype:AutoCompleteTextView
    lateinit var email: EditText
    lateinit var pass: EditText
    lateinit var forgot:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        signup=findViewById(R.id.txtsignup)
        button=findViewById(R.id.btnlogin)
        email=findViewById(R.id.etloginemail)
        pass=findViewById(R.id.etloginpass)
        loginusertype=findViewById(R.id.loginautoComplete)
        forgot=findViewById(R.id.txtforgotline)
        email.getText().clear()
        pass.getText().clear()

        val user=resources.getStringArray(R.array.usertype)
        val array= ArrayAdapter(this,R.layout.dropdown_item,user)
        val autocompletetv=findViewById<AutoCompleteTextView>(R.id.loginautoComplete)
        autocompletetv.setAdapter(array)


        var helper=MyDBHelper(applicationContext)
        var db=helper.readableDatabase

        forgot.setOnClickListener {
            val intent = Intent(this@LoginActivity, ForgotActivity::class.java)
            startActivity(intent)
        }
        button.setOnClickListener {
            var loginemail=email.text.toString()
            var loginpass=pass.text.toString()
            var loginut1="BUYER"
            var buyorpro2=loginusertype.text.toString()
            var args= listOf<String>(loginemail,loginpass).toTypedArray()
            var rs=db.rawQuery("SELECT * FROM BUYERUSERS WHERE EMAIL=? AND PWD=?",args)
            var rs2=db.rawQuery("SELECT * FROM PRODUCERUSERS WHERE EMAIL=? AND PWD=?",args)
            if((loginemail.isEmpty()) || loginpass.isEmpty()){
                Toast.makeText(this@LoginActivity, "Please Enter the Credentials", Toast.LENGTH_SHORT)
                    .show()
            }
            else {
                if (loginut1.equals(buyorpro2)) {
                    if (rs.moveToNext()) {
                        Toast.makeText(
                            this@LoginActivity,
                            "Buyer's Login Successful!!",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        intent.putExtra("USERNAME", rs.getString(1))
                        intent.putExtra("EMAIL", loginemail)
                        intent.putExtra("USERTYPE", "BUYER")
                        intent.putExtra("AADHARNUMBER",rs.getString(2))
                        intent.putExtra("PHONENUMBER",rs.getString(3))
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            "Buyer Account Doesn't Exist",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        email.getText().clear()
                        pass.getText().clear()
                        email.requestFocus()
                    }
                } else {
                    if (rs2.moveToNext()) {
                        Toast.makeText(
                            this@LoginActivity,
                            "Producer's Login Successful!!",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        intent.putExtra("EMAIL", loginemail)
                        intent.putExtra("USERNAME", rs2.getString(1))
                        intent.putExtra("USERTYPE", "PRODUCER")
                        intent.putExtra("AADHARNUMBER",rs2.getString(2))
                        intent.putExtra("PHONENUMBER",rs2.getString(3))
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            "Producer Account Doesn't Exist",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        email.getText().clear()
                        pass.getText().clear()
                        email.requestFocus()
                    }
                }
            }
        }
        signup.setOnClickListener {
        val intent=Intent(this@LoginActivity,SignUpActivity::class.java)
            startActivity(intent)
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
        Toast.makeText(this@LoginActivity, "Press Again to Exit", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }
}