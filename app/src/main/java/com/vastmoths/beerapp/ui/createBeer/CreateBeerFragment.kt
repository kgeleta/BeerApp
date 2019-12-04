package com.vastmoths.beerapp.ui.createBeer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.vastmoths.beerapp.R

class CreateBeerFragment : Fragment() {

    private lateinit var createBeerViewModel: CreateBeerViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        createBeerViewModel =
            ViewModelProviders.of(this).get(CreateBeerViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_create_beer, container, false)
        val textView: TextView = root.findViewById(R.id.text_create_beer)
        createBeerViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}