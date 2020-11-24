package com.zefirratstudio.waterbalancetracker.ui.home

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.zefirratstudio.waterbalancetracker.R


class HomeFragment : Fragment() {
    private var homeViewModel: HomeViewModel? = null
    private lateinit var _todayAmount: String
    private lateinit var _dailyAmountView: View
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView = root.findViewById<TextView?>(R.id.text_home)
        _dailyAmountView = root.findViewById<View>(R.id.dailyAmountView)
        homeViewModel!!.getText()?.observe(viewLifecycleOwner, Observer { s -> textView?.setText(s) })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val display = activity!!.windowManager.defaultDisplay
        var displayMetrics = DisplayMetrics()
        display.getMetrics(displayMetrics)
        homeViewModel?.DisplayHeight = displayMetrics.heightPixels
        homeViewModel?.DailyAmountView = _dailyAmountView
        homeViewModel?.OnViewCreated()
    }
}