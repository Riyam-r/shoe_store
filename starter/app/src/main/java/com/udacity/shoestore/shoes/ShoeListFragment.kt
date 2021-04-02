package com.udacity.shoestore.shoes

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoeListViewModel
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import timber.log.Timber


class ShoeListFragment : Fragment() {

    //private val shoeListingsViewModel: ShoeListViewModel by activityViewModels()

    lateinit var viewModel : ShoeListViewModel
    lateinit var binding : FragmentShoeListBinding
    lateinit var parentLayout: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater , R.layout.fragment_shoe_list , container , false)
        binding.lifecycleOwner = this

        binding.fabAddShoe.setOnClickListener({
            findNavController().navigate(ShoeListFragmentDirections.actionShoesFragmentToShoeDetailFragment())
        })

        viewModel = ViewModelProvider(requireActivity()).get(ShoeListViewModel::class.java)


        viewModel.shoeList.observe(viewLifecycleOwner , Observer { shoeList ->
            displayList(shoeList)
        })

        parentLayout = binding.llParent
        setHasOptionsMenu(true)

        // Inflate the layout for this fragment
            return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    // Menu item id must match the destination id to navigate there
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    private fun displayList (shoeList : MutableList<Shoe>){
        for (shoe in shoeList) {
            addNewShoeView(shoe)
        }
    }


    private fun addNewShoeView(shoe : Shoe) {

        //Maybe we can iterate over the shoe properties instead of writing each one in new line?
        // add it to container
        var llName : LinearLayout = getHorizontalview()
        llName.addView(createTextView(getString(R.string.shoe_name) + ":"))
        llName.addView(createTextView(shoe.name))
        parentLayout.addView(llName)

        var llCompany : LinearLayout = getHorizontalview()
        llCompany.addView(createTextView(getString(R.string.company) + ":"))
        llCompany.addView(createTextView(shoe.company))
        parentLayout.addView(llCompany)

        var llSize : LinearLayout = getHorizontalview()
        llSize.addView(createTextView(getString(R.string.shoe_size) + ":"))
        llSize.addView(createTextView(shoe.size.toString()))
        parentLayout.addView(llSize)

        var llDescription : LinearLayout = getHorizontalview()
        llDescription.addView(createTextView(getString(R.string.description) + ":"))
        llDescription.addView(createTextView(shoe.description))
        parentLayout.addView(llDescription)

        parentLayout.addView(createLine())

    }

    private fun createTextView(text: String): TextView {
        var textView = TextView(context)
        textView.text = text
        textView.textSize = 13f
        textView.isAllCaps = true
        val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        )

        params.setMargins(10, 10, 10, 10)
        textView.layoutParams = params

        return textView
    }

    private fun getHorizontalview () : LinearLayout {
        var container = LinearLayout(context)
        container.orientation = LinearLayout.HORIZONTAL
        return container
    }


    private fun createLine () : View {
        var view = View(context)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        view.setBackgroundColor(Color.GRAY)
        params.setMargins(0, 15, 0  , 15)
        params.height = 5
        view.layoutParams = params

        return view
    }

}