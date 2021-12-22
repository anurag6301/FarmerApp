package com.anurag.farmersportal

import android.app.ProgressDialog
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Patterns
import android.widget.*
import androidx.appcompat.widget.ThemedSpinnerAdapter
import androidx.core.content.ContentProviderCompat.requireContext
import com.gospelware.liquidbutton.LiquidButton
import java.lang.Exception
import kotlin.math.sign
import android.app.ProgressDialog.show as progressDialogShow
import android.widget.ArrayAdapter as ArrayAdapter1
import android.widget.ArrayAdapter as ArrayAdapter2

class SignUpActivity : AppCompatActivity() {
    lateinit var buyername:EditText
    lateinit var aadhar:EditText
    lateinit var phone:EditText
    lateinit var email:EditText
    lateinit var pass:EditText
    lateinit var setnickname:EditText
    lateinit var cnfrmpass:EditText
    lateinit var button:Button
    lateinit var usertype:AutoCompleteTextView
    lateinit var haveaccount:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        buyername=findViewById(R.id.etname)
        aadhar=findViewById(R.id.etaadharnumber)
        phone=findViewById(R.id.etphone)
        email=findViewById(R.id.etsignemail)
        pass=findViewById(R.id.etsignpass)
        setnickname=findViewById(R.id.etsetnickname)
        haveaccount=findViewById(R.id.haveanaccount)
        cnfrmpass=findViewById(R.id.etsigncnfrmpass)
        button=findViewById(R.id.btnsignup)
        usertype=findViewById(R.id.autoComplete)
        val user=resources.getStringArray(R.array.usertype)
        val array= ArrayAdapter2(this,R.layout.dropdown_item,user)
        val autocompletetv=findViewById<AutoCompleteTextView>(R.id.autoComplete)
        autocompletetv.setAdapter(array)

        button.setOnClickListener {
        signinuser()
        }
        haveaccount.setOnClickListener {
            val intent=Intent(this@SignUpActivity,LoginActivity::class.java)
            startActivity(intent)
        }
    }
    private fun signinuser(){
     var name=buyername.text.toString()
     var funaadhar=aadhar.text.toString()
     var funphone=phone.text.toString()
     var gmail=email.text.toString()
     var funpass=pass.text.toString()
     var confirm=cnfrmpass.text.toString()
     var buyorpro=usertype.text.toString()
     var funnickname=setnickname.text.toString()
     var type1="BUYER"
     if(name.isEmpty()) {
         buyername.setError("Please Enter Username")
     }
     else if(buyorpro.isEmpty()){
         usertype.setError("Please Select One Option")
     }
     else if((funaadhar.isEmpty()) || ((funaadhar.length)!=12)){
         aadhar.setError("Should contain 12 numbers")
     }
     else if ((funphone.isEmpty())){
         phone.setError("Please Enter the Phone Number")
     }

     else if(!gmail.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(gmail).matches()){
        if(!confirm.equals(funpass))
        {
            Toast.makeText(this@SignUpActivity,"Password does not match!!",Toast.LENGTH_SHORT).show()
        }
         else{
            var helper=MyDBHelper(applicationContext)
            var db=helper.readableDatabase
            var args= listOf<String>(gmail,funaadhar).toTypedArray()
            var rs=db.rawQuery("SELECT * FROM BUYERUSERS WHERE EMAIL=? OR USERAADHAR=?",args)
            var rs2=db.rawQuery("SELECT * FROM PRODUCERUSERS WHERE EMAIL=? OR USERAADHAR=?",args)
            var cv= ContentValues()
                cv.put("USERNAME",name)
                cv.put("USERAADHAR",funaadhar)
                cv.put("PHONE",funphone)
                cv.put("EMAIL",gmail)
                cv.put("NICKNAME",funnickname)
                cv.put("PWD",funpass)
                if(buyorpro.equals(type1)) {
                    if(rs.moveToNext()){
                        aadhar.setError("Please Enter Correct Aadhar Number")
                        email.setError("Please Enter New Email")
                        aadhar.requestFocus()
                        Toast.makeText(this@SignUpActivity,"Email and Aadhar Number Already Registered!!",Toast.LENGTH_SHORT).show()
                    }else{
                        db.insert("BUYERUSERS", null, cv)
                        Create()
                    }

                }else{
                    if(rs2.moveToNext()){
                        aadhar.setError("Please Enter Correct Aadhar Number")
                        email.setError("Please Enter New Email")
                        aadhar.requestFocus()
                        Toast.makeText(this@SignUpActivity,"Email and Aadhar Number Already Registered!!",Toast.LENGTH_SHORT).show()
                    }else{
                     db.insert("PRODUCERUSERS", null, cv)
                        Create()}
                }
         }
     }
     else if(funnickname.isEmpty()){
         setnickname.setError("Please Enter Nickname")
     }
     else if(funpass.isEmpty()){
         pass.setError("Please Set Password")
     }
     else if (confirm.isEmpty()){
         cnfrmpass.setError("Please Enter Confirm Password")
     }
     else{
            Toast.makeText(this@SignUpActivity,"Please Enter the Credentials",Toast.LENGTH_SHORT).show()
     }
     }
    fun Create(){
        /*val progressDialog=ProgressDialog(this@SignUpActivity)
        progressDialog.setTitle("Creating New Account")
        progressDialog.setMessage("Please wait while we are creating for you")
        progressDialog.show()
        Toast.makeText(this@SignUpActivity,"Registered Successfully!!",Toast.LENGTH_SHORT).show()
        val intent= Intent(this@SignUpActivity,LoginActivity::class.java)
        startActivity(intent)*/
        val intent= Intent(this@SignUpActivity,LiquidLoadingActivity::class.java)
        startActivity(intent)
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
        val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
        startActivity(intent)

        Handler(Looper.getMainLooper()).postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }

}