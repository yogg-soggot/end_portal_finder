package com.kthulhu.endportalfinder.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.kthulhu.endportalfinder.R
import com.kthulhu.endportalfinder.di.AppComponent
import com.kthulhu.endportalfinder.di.AppModule
import com.kthulhu.endportalfinder.di.DaggerAppComponent
import com.kthulhu.endportalfinder.di.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var appComponent: AppComponent

    @Inject
    lateinit var vmFactory: ViewModelFactory

    val mainViewModel: MainViewModel by viewModels { vmFactory }

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(applicationContext))
            .build()

        appComponent.inject(this)

        setSupportActionBar(toolbar)

        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_finder,
                R.id.nav_portals,
                R.id.nav_usage_guide
            ),
            drawer_layout
        )


        setupActionBarWithNavController(navController, appBarConfiguration)
        nav_view.setupWithNavController(navController)
        nav_view.itemIconTintList = null

        val gitHubLink: TextView = nav_view.getHeaderView(0).findViewById(R.id.github_link)
        gitHubLink.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun sendBugReport(){
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(resources.getString(R.string.my_email)))
            putExtra(Intent.EXTRA_SUBJECT, resources.getString(R.string.email_subject))
        }
        startActivity(Intent.createChooser(intent, resources.getString(R.string.bug_report)))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_bug_report -> {
                sendBugReport()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}