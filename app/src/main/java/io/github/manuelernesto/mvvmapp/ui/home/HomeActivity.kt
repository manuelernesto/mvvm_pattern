package io.github.manuelernesto.mvvmapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import io.github.manuelernesto.mvvmapp.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var mNavController: NavController
    private lateinit var mDrawer: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)


        mNavController = Navigation.findNavController(this, R.id.fragment)
        mDrawer = findViewById(R.id.drawer_layout)

        NavigationUI.setupWithNavController(nav_view, mNavController)
        NavigationUI.setupActionBarWithNavController(this, mNavController, mDrawer)

    }


    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(mNavController, mDrawer)
    }


}
