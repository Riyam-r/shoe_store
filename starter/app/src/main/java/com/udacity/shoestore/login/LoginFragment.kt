package com.udacity.shoestore.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.models.User

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding;
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // View binding - Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        // Data binding - Link fragment to view model
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.loginViewModel = viewModel
        binding.setLifecycleOwner(this)

        binding.userModel = User("" , "")

        // Observers
        viewModel.isAuthorized.observe(viewLifecycleOwner , Observer { isAuthorized->
            if (isAuthorized) {
               findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
            }
        })


        return binding.root
    }

}