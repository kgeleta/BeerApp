package com.vastmoths.beerapp.ui.detailBeer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.vastmoths.beerapp.R

class DetailBeerFragment : Fragment() {

    private lateinit var detailBeerViewModel: DetailBeerViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailBeerViewModel =
            ViewModelProviders.of(this).get(DetailBeerViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_detail_beer, container, false)
        val textView: TextView = root.findViewById(R.id.text_tools)
        detailBeerViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}