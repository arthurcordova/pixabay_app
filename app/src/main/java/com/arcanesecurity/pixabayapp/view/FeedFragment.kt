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
import com.arcanesecurity.pixabayapp.adapter.HeaderAdapter
import com.arcanesecurity.pixabayapp.databinding.FeedFragmentBinding
import com.arcanesecurity.pixabayapp.model.Image
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : Fragment(R.layout.feed_fragment) {

    companion object {
        fun newInstance() = FeedFragment()
    }

    private lateinit var viewModel: FeedViewModel
    private lateinit var binding: FeedFragmentBinding
    lateinit var adapters: ConcatAdapter
    private val adapterFeed = FeedAdapter()
    private val adapterHeader = HeaderAdapter {
        viewModel.fetchImages(it)
    }

    private val observerImages = Observer<List<Image>> {
        adapterFeed.submitList(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FeedFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        viewModel.images.observe(viewLifecycleOwner, observerImages)

        adapters = ConcatAdapter(AdsAdapter(), adapterHeader, adapterFeed)
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

        viewModel.fetchImages()
    }


}

fun View.hideSoftInput() {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}