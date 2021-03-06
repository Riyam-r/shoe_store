package com.udacity.shoestore.shoes

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
import com.udacity.shoestore.ShoeListViewModel
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.User
import timber.log.Timber


class ShoeDetailFragment : Fragment() {

    lateinit var binding : FragmentShoeDetailBinding
    lateinit var viewModel : ShoeListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // View binding
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_shoe_detail , container , false)
        binding.lifecycleOwner = this
        // Data binding - shared view model
        viewModel = ViewModelProvider(requireActivity()).get(ShoeListViewModel::class.java)
        binding.shoeListViewModel = viewModel

        binding.shoeModel = Shoe()


        viewModel.shoeList.observe(viewLifecycleOwner , Observer { shoeList ->

        })

        //Observers
        viewModel.isCancelAdd.observe(viewLifecycleOwner , Observer { isCancel ->
            if (isCancel) {
                viewModel.setIsCancel(false) // reset for next visit value
                findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoesFragment())
            }
        })

        viewModel.isAddShoe.observe(viewLifecycleOwner , Observer { isAdd ->
            if (isAdd) {
                viewModel.setIsAddShoe(false); //reset value for next visit
                findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoesFragment())
            }
        })

        // Inflate the layout for this fragment
        return binding.root
    }

}