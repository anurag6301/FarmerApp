package com.anurag.farmersportal.ui.News_Articles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class News_Articles_ViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is News_Articles Fragment"
    }
    val text: LiveData<String> = _text
}