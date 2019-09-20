package com.binhnguyen.newsapp.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.binhnguyen.newsapp.databinding.FragmentNewsBinding
import com.binhnguyen.newsapp.ui.news.NewsRecyclerAdapter.OnClickListener

open class NewsFragment : Fragment() {

    protected lateinit var viewModel: NewsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val binding = FragmentNewsBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        initViewModel()
        enableFilter()
        binding.viewModel = viewModel
        binding.newsList.adapter = NewsRecyclerAdapter(OnClickListener {
            viewModel.displayNewsDetails(it)
        })

        viewModel.navigateToSelectedNews.observe(this, Observer {
            if ( it != null ) {
                this.findNavController().navigate(NewsFragmentDirections.actionShowDetail(it))
                viewModel.displayNewsDetailsComplete()
            }
        })
        return binding.root
    }

    protected open fun enableFilter() {}

    protected open fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
    }
}