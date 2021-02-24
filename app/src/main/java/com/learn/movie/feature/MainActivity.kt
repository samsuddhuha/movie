package com.learn.movie.feature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.learn.movie.R
import com.learn.movie.databinding.ActivityMainBinding
import com.learn.movie.feature.account.AccountFragment
import com.learn.movie.feature.information.InformationFragment
import com.learn.movie.feature.playing.PlayingFragment
import com.learn.movie.feature.ticket.TicketFragment
import com.learn.movie.support.extension.toast
import com.learn.movie.support.extension.viewBinding
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
    }

    private fun setupView() {
        binding.navView.setOnNavigationItemSelectedListener(this)
        mapMenu(4)
    }

    fun mapMenu(data: Int){
        when(data){
            4 -> {
                binding.navView.selectedItemId = R.id.main_now_playing
            }
            3 -> {
                binding.navView.selectedItemId = R.id.main_information
            }
            2 -> {
                binding.navView.selectedItemId = R.id.main_ticket
            }
            else -> {
                binding.navView.selectedItemId = R.id.main_account
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        when (item.itemId) {
            R.id.main_now_playing -> fragment = PlayingFragment()
            R.id.main_information -> fragment = InformationFragment()
            R.id.main_ticket -> fragment = TicketFragment()
            R.id.main_account -> fragment = AccountFragment()
        }
        return loadFragment(fragment)
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host, fragment)
                .commit()
            return true
        }

        return false
    }

    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            moveTaskToBack(true)
            exitProcess(-1)
            finish()
            return
        }

        this.doubleBackToExitPressedOnce = true
        toast(this, "Press back button again to exit")

        Handler().postDelayed(Runnable {
            doubleBackToExitPressedOnce = false
        }, 2000)
    }
}