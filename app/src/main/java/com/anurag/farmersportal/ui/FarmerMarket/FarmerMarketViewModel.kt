package com.anurag.farmersportal.ui.FarmerMarket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FarmerMarketViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Farmer's Market Fragment"
    }
    val text: LiveData<String> = _text
}