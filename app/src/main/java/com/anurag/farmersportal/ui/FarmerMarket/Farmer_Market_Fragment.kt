package com.anurag.farmersportal.ui.FarmerMarket

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.anurag.farmersportal.R
import com.anurag.farmersportal.databinding.FragmentGalleryBinding
import com.anurag.farmersportal.ui.home.HomeFragment

class Farmer_Market_Fragment : Fragment() {

  private lateinit var farmerMarketViewModel: FarmerMarketViewModel
  private var _binding: FragmentGalleryBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    farmerMarketViewModel =
            ViewModelProvider(this).get(FarmerMarketViewModel::class.java)

    _binding = FragmentGalleryBinding.inflate(inflater, container, false)
    val root: View = binding.root

    val textView: TextView = binding.textGallery
    farmerMarketViewModel.text.observe(viewLifecycleOwner, Observer {
      textView.text = it
    })
    return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}