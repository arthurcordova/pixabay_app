package com.arcanesecurity.pixabayapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arcanesecurity.pixabayapp.view.FeedFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, FeedFragment.newInstance()).commitNow()

    }
}