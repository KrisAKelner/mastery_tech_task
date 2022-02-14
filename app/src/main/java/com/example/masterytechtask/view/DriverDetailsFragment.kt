package com.example.masterytechtask.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.masterytechtask.R
import com.example.masterytechtask.databinding.FragmentDriverDetailsBinding
import com.example.masterytechtask.model.Driver

class DriverDetailsFragment : Fragment() {

    private var _binding: FragmentDriverDetailsBinding? = null

    private lateinit var driver: Driver

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDriverDetailsBinding.inflate(inflater, container, false)
        val args: DriverDetailsFragmentArgs by navArgs()
        driver = args.driver
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateInfo()
    }

    private fun populateInfo() {
        binding.detailsFirstNameTxt.text = driver.firstName
        binding.detailsLastNameTxt.text = driver.lastName
        binding.detailsPhoneNumberTxt.text = driver.phoneNumber
        binding.detailsTrailerTypeTxt.text = driver.details.trailerType
        binding.detailsTrailerDimensionsTxt.text = String.format(getString(R.string.dimens), driver.details.trailerLength, driver.details.trailerHeight, driver.details.trailerWidth)
        binding.detailsPlateNumberTxt.text = driver.details.plateNumber
        binding.detailsLocationTxt.text = String.format(getString(R.string.latlong), driver.details.currentLocation.latitude, driver.details.currentLocation.longitude)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}