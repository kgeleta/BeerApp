package com.vastmoths.beerapp.ui.detailBeer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailBeerViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is tools Fragment"
    }
    val text: LiveData<String> = _text
}