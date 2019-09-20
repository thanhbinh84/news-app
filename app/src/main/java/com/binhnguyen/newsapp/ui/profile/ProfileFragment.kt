package com.binhnguyen.newsapp.ui.profile

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.binhnguyen.newsapp.R
import com.binhnguyen.newsapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? {
        val binding = FragmentProfileBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        viewModel =
            ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.isRegistered.observe(this, Observer {
            if (it) {
                binding.registerContainer.visibility = View.GONE
                binding.greetingContainer.visibility = View.VISIBLE
            } else {
                binding.registerContainer.visibility = View.VISIBLE
                binding.greetingContainer.visibility = View.GONE
            }
        })
        binding.registerButton.setOnClickListener {
            viewModel.register(binding.usernameEdittext.text.toString())
        }
        viewModel.isInvalidInput.observe(this, Observer {
            if (it)
                binding.usernameTextInputLayout.error = getString(R.string.invalid)
            else
                binding.usernameTextInputLayout.error = null
        })
        binding.usernameEdittext.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                viewModel.clearError()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        return binding.root
    }
}