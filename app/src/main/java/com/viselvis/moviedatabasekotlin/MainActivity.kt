package com.viselvis.moviedatabasekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.viselvis.moviedatabasekotlin.databinding.ActivityMainBinding
import com.viselvis.moviedatabasekotlin.fragments.AccountFragment
import com.viselvis.moviedatabasekotlin.fragments.ContactFragment
import com.viselvis.moviedatabasekotlin.fragments.SearchFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.bottomNavView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.accountOption -> {
                    supportFragmentManager.beginTransaction().replace(R.id.mainFrame, AccountFragment()).commit()
                }
                R.id.contactOption -> {
                    supportFragmentManager.beginTransaction().replace(R.id.mainFrame, ContactFragment()).commit()
                }
                R.id.searchOption -> {
                    supportFragmentManager.beginTransaction().replace(R.id.mainFrame, SearchFragment()).commit()
                }
            }
            true
        }

        supportFragmentManager.beginTransaction().replace(R.id.mainFrame, SearchFragment()).commit()
    }
}