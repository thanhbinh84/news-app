package com.binhnguyen.newsapp.ui.custom

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.binhnguyen.newsapp.R
import com.binhnguyen.newsapp.network.ApiFilter
import com.binhnguyen.newsapp.ui.news.NewsFragment

class CustomFragment : NewsFragment() {

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(CustomViewModel::class.java)
    }

    override fun enableFilter() {
        setHasOptionsMenu(true)
        (viewModel as CustomViewModel).keyword.observe(this, Observer {
            (activity as AppCompatActivity).supportActionBar?.setTitle(
                getString(
                    R.string.custom_news,
                    it
                )
            )
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        (viewModel as CustomViewModel).updateFilter(
            when (item.itemId) {
                R.id.bitcoin_menu -> ApiFilter.BITCOIN
                R.id.apple_menu -> ApiFilter.APPLE
                R.id.earthquake_menu -> ApiFilter.EARTHQUAKE
                else -> ApiFilter.ANIMAL
            }
        )
        return true
    }
}