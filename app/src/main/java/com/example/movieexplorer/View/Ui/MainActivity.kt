package com.example.movieexplorer.View.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelStoreOwner
import com.example.movieexplorer.R
import com.google.android.material.bottomnavigation.BottomNavigationView
private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate call")


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationViewId)
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationListener)
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