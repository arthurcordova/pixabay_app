package com.arcanesecurity.pixabayapp.view

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arcanesecurity.pixabayapp.R
import com.arcanesecurity.pixabayapp.adapter.AdsAdapter
import com.arcanesecurity.pixabayapp.adapter.FeedAdapter
import com.arcanesecurity.pixabayapp.adapter.FeedVideoAdapter
import com.arcanesecurity.pixabayapp.adapter.HeaderAdapter
import com.arcanesecurity.pixabayapp.databinding.FeedFragmentBinding
import com.arcanesecurity.pixabayapp.model.Image
import com.arcanesecurity.pixabayapp.model.VideoConfig
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment(private val feedType: FeedType) : Fragment(R.layout.feed_fragment) {

    private lateinit var viewModel: FeedViewModel
    private lateinit var binding: FeedFragmentBinding
    lateinit var adapters: ConcatAdapter
    private val adapterFeed = FeedAdapter()
    private val adapterVideo = FeedVideoAdapter()
//    private val adapterHeader = HeaderAdapter {
//        viewModel.fetchImages(it)
//    }

    private val observerImages = Observer<List<Image>> {
        adapterFeed.submitList(it)
    }

    private val observerVideos = Observer<List<VideoConfig>> {
        adapterVideo.submitList(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FeedFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        viewModel.images.observe(viewLifecycleOwner, observerImages)
        viewModel.videos.observe(viewLifecycleOwner, observerVideos)

        adapters = if (feedType == FeedType.VIDEO) ConcatAdapter(adapterVideo) else ConcatAdapter(adapterFeed)
        setupRecyclerView()
    }

    private fun setupRecyclerView() = with(binding.recyclerViewFeed) {
        adapter = adapters
        layoutManager = LinearLayoutManager(requireContext())
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                hideSoftInput()
            }
        })

        if (feedType == FeedType.VIDEO) viewModel.fetchVideo() else viewModel.fetchImages()
    }
}

enum class FeedType{
    VIDEO,
    IMAGE
}

fun View.hideSoftInput() {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}