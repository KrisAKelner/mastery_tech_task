package com.example.masterytechtask.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.masterytechtask.databinding.FragmentDriverListBinding
import com.example.masterytechtask.recyclerview.DriverListAdapter
import com.example.masterytechtask.viewmodel.DriverListViewModel
import com.google.android.material.snackbar.Snackbar


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DriverListFragment : Fragment() {

    private var _binding: FragmentDriverListBinding? = null

    private lateinit var driverListViewModel: DriverListViewModel

    lateinit var adapter: DriverListAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDriverListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        driverListViewModel = ViewModelProvider(this)[DriverListViewModel::class.java]

        driverListViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    displayError(it)
                }
            }
        )

        driverListViewModel.driverList.observe(viewLifecycleOwner, Observer {
                adapter = DriverListAdapter(it, object : DriverListAdapter.OnItemClickListener {
                    override fun onItemClick(position: Int) {
                        val driver = adapter.items[position]
                        val directions = DriverListFragmentDirections.actionDriverListFragmentToDriverDetailsFragment(driver)
                        findNavController().navigate(directions)
                    }
                })
                binding.driverListRecyclerView.adapter = adapter
            }
        )


        val recyclerView = binding.driverListRecyclerView
        val layoutManager = LinearLayoutManager(context)

        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            layoutManager.orientation
        )

        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(dividerItemDecoration)

        driverListViewModel.loadDriverList()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun displayError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }
}