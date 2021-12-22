package com.anurag.farmersportal.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "The Easy and Efficient Market of everyone..."
    }
    val text: LiveData<String> = _text
}