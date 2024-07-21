package com.example.serise_on_clone_coding

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.serise_on_clone_coding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.fragment_container, MovieFragment())
        }
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.movie -> {
                    MovieFragment().loadFragment()
                    Log.i("BOTTOM", "movie")
                    true
                }
                R.id.on_air -> {
                    Log.i("BOTTOM", "on air")
                    true
                }
                R.id.membership -> {
                    Log.i("BOTTOM", "membership")
                    true
                }
                R.id.search -> {
                    Log.i("BOTTOM", "search")
                    true
                }
                R.id.my -> {
                    Log.i("BOTTOM", "my")
                    true
                }
                else -> false
            }
        }
    }

    private fun Fragment.loadFragment() {
        supportFragmentManager.commit {
            replace(R.id.fragment_container, this@loadFragment)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }
}