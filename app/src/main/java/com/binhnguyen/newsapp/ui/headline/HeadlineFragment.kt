package com.binhnguyen.newsapp.ui.headline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.binhnguyen.newsapp.databinding.FragmentHeadlineBinding

class HeadlineFragment : Fragment() {

    private val headlineViewModel: HeadlineViewModel by lazy {
        ViewModelProviders.of(this).get(HeadlineViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val binding = FragmentHeadlineBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        binding.viewModel = headlineViewModel
        binding.newsList.adapter = NewsRecyclerAdapter()
        return binding.root
    }
}