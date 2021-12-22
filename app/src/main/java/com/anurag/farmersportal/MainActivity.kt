package com.anurag.farmersportal
import android.content.ActivityNotFoundException
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.anurag.farmersportal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var username:TextView
    lateinit var email:TextView
    lateinit var usertype:TextView
    lateinit var hview:View
    lateinit var card1:TextView
    lateinit var card2:TextView
    lateinit var card3:TextView

private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     binding = ActivityMainBinding.inflate(layoutInflater)
     setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            /*var intent=Intent(Intent.ACTION_DIAL)
            intent.setData(Uri.parse("tel:9876543210"))*/
            var string="mailto:info@farmersportal.com"
            var intent=Intent(Intent.ACTION_SENDTO)
            intent.putExtra(Intent.EXTRA_SUBJECT,"SUBJECT")
            intent.setData(Uri.parse(string))
            try{
            startActivity(intent)
            } catch (e: ActivityNotFoundException){
                Toast.makeText(this@MainActivity, "Not Working", Toast.LENGTH_SHORT).show()
            }
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_home, R.id.nav_gallery,R.id.nav_myaccount,R.id.nav_slideshow,R.id.nav_contact,R.id.nav_logout), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        hview=navView.getHeaderView(0)

        username=hview.findViewById(R.id.txtprofilename)
        email=hview.findViewById(R.id.profileemail)
        usertype=hview.findViewById(R.id.profilusertype)
        card1=findViewById(R.id.txtcard11)
        card2=findViewById(R.id.txtcard21)
        card3 =findViewById(R.id.txtcard31)
        val intentemail=intent.getStringExtra("EMAIL")
        val intentusername=intent.getStringExtra("USERNAME")
        val intentusertype=intent.getStringExtra("USERTYPE")
        email.setText(intentemail)
        username.setText(intentusername)
        usertype.setText(intentusertype)
        var helper=MyDBHelper(applicationContext)
        var db=helper.readableDatabase
        var rs=db.rawQuery("SELECT * FROM BUYERUSERS",null)
        var rs2=db.rawQuery("SELECT * FROM PRODUCERUSERS ",null)
        if(rs.moveToLast()) {
            card1.setText(rs.getString(0))
        }
        if(rs2.moveToLast()){
            card2.setText(rs2.getString(0))
        }
        navView.setupWithNavController(navController)
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
        Toast.makeText(this@MainActivity, "Press Again to Exit", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}