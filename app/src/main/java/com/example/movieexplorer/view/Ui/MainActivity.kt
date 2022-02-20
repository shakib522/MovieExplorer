package com.example.movieexplorer.view.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.movieexplorer.R
import com.example.movieexplorer.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationViewId.setOnNavigationItemSelectedListener(navigationListener)
        supportFragmentManager.beginTransaction().replace(R.id.frameLayoutId, MovieFragment()).commit()
    }

    private val navigationListener = BottomNavigationView.OnNavigationItemSelectedListener {
        var selectedFragment: Fragment? = null
        when (it.itemId) {
            R.id.movieId -> {
                selectedFragment = MovieFragment()
            }
            R.id.tvId -> {
                selectedFragment = TvFragment()
            }
            R.id.trendingId -> {
                selectedFragment = TrendingFragment()
            }
        }
        if(selectedFragment!=null){
            supportFragmentManager.beginTransaction().replace(R.id.frameLayoutId,selectedFragment).commit()
        }
        true
    }

}