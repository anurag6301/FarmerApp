package com.anurag.farmersportal.ui.MyAccount

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.anurag.farmersportal.MainActivity
import com.anurag.farmersportal.MyDBHelper
import com.anurag.farmersportal.R
import org.w3c.dom.Text

class MyAccountFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        var namedata=root.findViewById<TextView>(R.id.profilename)
        var email=root.findViewById<TextView>(R.id.profilemailmy)
        var button=root.findViewById<TextView>(R.id.btnbackhome)
        var activity = getActivity() as MainActivity
        var name: String? = activity.getMyname()
        var dataemail: String? = activity.getMyemail()
        var usertype:String?=activity.getMyusertype()
        var phone:String?=activity.getMynumber()
        var aadhar:String?=activity.getMyAadhar()
        namedata.setText(name)
        email.setText(dataemail)
        (root.findViewById<TextView>(R.id.nameTextView)).setText(name)
        (root.findViewById<TextView>(R.id.usertype)).setText(usertype)
        (root.findViewById<TextView>(R.id.txtemail)).setText(dataemail)
        (root.findViewById<TextView>(R.id.txtphonenumber)).setText(phone)
        (root.findViewById<TextView>(R.id.txtaadhar)).setText(aadhar)

        return root
    }
}