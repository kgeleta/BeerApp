package com.vastmoths.beerapp.ui.createBeer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateBeerViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Here you will create beer"
    }
    val text: LiveData<String> = _text
}