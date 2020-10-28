package com.zefirratstudio.waterbalancetracker.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.zefirratstudio.waterbalancetracker.R
import org.junit.runner.RunWith

class SettingsFragment : Fragment() {
    private var slideshowViewModel: SettingsViewModel? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        slideshowViewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_settings, container, false)
        val textView = root.findViewById<TextView?>(R.id.text_slideshow)
        slideshowViewModel.getText().observe(viewLifecycleOwner, Observer { s -> textView.setText(s) })
        return root
    }
}