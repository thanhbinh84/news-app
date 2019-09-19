package com.binhnguyen.newsapp.ui.headline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.binhnguyen.newsapp.R

class HeadlineFragment : Fragment() {

    private lateinit var headlineViewModel: HeadlineViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        headlineViewModel =
            ViewModelProviders.of(this).get(HeadlineViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        headlineViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}