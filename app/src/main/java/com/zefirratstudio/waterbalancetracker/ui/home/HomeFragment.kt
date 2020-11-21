package com.zefirratstudio.waterbalancetracker.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.zefirratstudio.waterbalancetracker.R
import com.zefirratstudio.waterbalancetracker.globalsettings.SettingsSingleton
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private var homeViewModel: HomeViewModel? = null
    private lateinit var _todayAmount: String
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView = root.findViewById<TextView?>(R.id.text_home)
        homeViewModel!!.getText()?.observe(viewLifecycleOwner, Observer { s -> textView?.setText(s) })
        return root
    }
}