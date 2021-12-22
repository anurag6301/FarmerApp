package com.anurag.farmersportal

import android.app.ProgressDialog
import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback

class ChangePasswordActivity : AppCompatActivity() {
    lateinit var pass:EditText
    lateinit var cnfrmpass:EditText
    lateinit var update: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_change_password)
        pass=findViewById(R.id.etchangepass)
        cnfrmpass=findViewById(R.id.etchangecnfrmpass)
        update=findViewById(R.id.btnutpass)
        update.setOnClickListener {
            updateuser()
        }
    }
fun updateuser() {
    val type1 = "BUYER"
    var funpass = pass.text.toString()
    var confirm = cnfrmpass.text.toString()
    var cusertype = intent.getStringExtra("USERTYPE")
    var chngemail = intent.getStringExtra("EMAIL")

    var helper = MyDBHelper(applicationContext)
    var db = helper.readableDatabase
    var cv = ContentValues()
    cv.put("PWD", funpass)
    if (confirm.equals(funpass)) {
            if (cusertype.equals(type1)) {
            db.update("BUYERUSERS", cv, "EMAIL=?", arrayOf(chngemail))
            } else {
            db.update("PRODUCERUSERS", cv, "EMAIL=?", arrayOf(chngemail))}

            Toast.makeText(this@ChangePasswordActivity, "Updated Successfully!!", Toast.LENGTH_SHORT).show()
            val progressDialog = ProgressDialog(this@ChangePasswordActivity)
            progressDialog.setTitle("Updating Password")
            progressDialog.setMessage("Please wait while we are updating for you")
            progressDialog.show()
            val intent = Intent(this@ChangePasswordActivity, LoginActivity::class.java)
                startActivity(intent)
    }
    else {
        Toast.makeText(this@ChangePasswordActivity,"Password Mismatch", Toast.LENGTH_SHORT).show()
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
            val intent = Intent(this@ChangePasswordActivity, LoginActivity::class.java)
            startActivity(intent)

            Handler(Looper.getMainLooper()).postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
        }

}

