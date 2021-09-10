package com.arcanesecurity.pixabayapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.arcanesecurity.pixabayapp.databinding.ActivityMainBinding
import com.arcanesecurity.pixabayapp.view.FeedFragment
import com.arcanesecurity.pixabayapp.view.FeedType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toggleButtonGroup.check(R.id.buttonImage)
        binding.toggleButtonGroup.addOnButtonCheckedListener {
                group, checkedId, isChecked ->
            when(checkedId) {
                R.id.buttonImage -> replaceFrag(FeedType.IMAGE)
                R.id.buttonVideos -> replaceFrag(FeedType.VIDEO)
            }
        }

        replaceFrag(FeedType.IMAGE)

    }

    fun replaceFrag(feedType: FeedType) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, FeedFragment(feedType)).commitNow()
    }

}