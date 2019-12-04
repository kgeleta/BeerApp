package com.vastmoths.beerapp.ui.listBeers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.vastmoths.beerapp.R

class ListBeersFragment : Fragment() {

    private lateinit var listBeersViewModel: ListBeersViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listBeersViewModel =
            ViewModelProviders.of(this).get(ListBeersViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_list_beer, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        listBeersViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}