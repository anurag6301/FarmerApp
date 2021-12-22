package com.anurag.farmersportal.ui.Contact

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.anurag.farmersportal.R
import com.anurag.farmersportal.databinding.FragmentHomeBinding
import com.anurag.farmersportal.ui.home.HomeViewModel

class ContactFragment  : Fragment() {
    lateinit var msgbutton: Button
    lateinit var username:EditText
    lateinit var email:EditText
    lateinit var number:EditText
    lateinit var subject:EditText
    lateinit var message:EditText
    lateinit var map:ImageView
    lateinit var locationcard:CardView
    lateinit var mailcard:CardView
        override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            val root = inflater.inflate(R.layout.fragment_contact,container,false)
            root.findViewById<CardView>(R.id.callcard).setOnClickListener {
                var intent= Intent(Intent.ACTION_DIAL)
                intent.setData(Uri.parse("tel:+91-9876543210"))
                startActivity(intent)
            }
            root.findViewById<CardView>(R.id.locationcard).setOnClickListener {
                var intent= Intent(Intent.ACTION_VIEW)
                intent.setData(Uri.parse("https://earth.google.com/web/search/Bhopal,+Madhya+Pradesh/@23.1994097,77.40589145,503.97046165a,68455.67527829d,35y,0h,0t,0r/data=CoABGlYSUAokMHgzOTdjNDI4ZjhmZDY4ZmJkOjB4MjE1NTcxNmQ1NzJkNGY4GTCkHv2KQjdAIbO1vkhoWlNAKhZCaG9wYWwsIE1hZGh5YSBQcmFkZXNoGAIgASImCiQJLGi0RPO2M0ARKmi0RPO2M8AZGvfNaErlM0AhM5C2Wh1DVMA"))
                startActivity(intent)
            }
            root.findViewById<ImageView>(R.id.imgmapbhopal).setOnClickListener {
                var intent= Intent(Intent.ACTION_VIEW)
                intent.setData(Uri.parse("https://goo.gl/maps/viUQRiQyRkEki94e9"))
                startActivity(intent)
            }
            root.findViewById<CardView>(R.id.emailcard).setOnClickListener {
                var string="mailto:info@farmersportal.com"
                var intent=Intent(Intent.ACTION_SENDTO)
                intent.putExtra(Intent.EXTRA_SUBJECT,"SUBJECT")
                intent.setData(Uri.parse(string))
                try{
                    startActivity(intent)
                } catch (e: ActivityNotFoundException){
                    //  Toast.makeText(this@ContactFragment, "Not Working", Toast.LENGTH_SHORT).show()
                }
            }
            var name=root.findViewById<EditText>(R.id.etname)
            var mail=root.findViewById<EditText>(R.id.etmail)
            var number=root.findViewById<EditText>(R.id.eturno)
            var subject=root.findViewById<EditText>(R.id.etsubject)
            var message=root.findViewById<EditText>(R.id.etmessage)
            root.findViewById<Button>(R.id.btnmsg).setOnClickListener {
                var string="mailto:info@farmersportal.com"
                var intent=Intent(Intent.ACTION_SENDTO)
                intent.setType("text/plain")
                intent.putExtra(Intent.EXTRA_EMAIL,mail.text.toString())
                intent.putExtra(Intent.EXTRA_SUBJECT,name.text.toString())
                intent.putExtra(Intent.EXTRA_SUBJECT,number.text.toString())
                intent.putExtra(Intent.EXTRA_SUBJECT,subject.text.toString())
                intent.putExtra(Intent.EXTRA_TEXT,message.text.toString())
                intent.setData(Uri.parse(string))
                startActivity(Intent.createChooser(intent,"Send Mail"))
                Log.i("Finished Sending Email","")
            }
            return root
        }
}