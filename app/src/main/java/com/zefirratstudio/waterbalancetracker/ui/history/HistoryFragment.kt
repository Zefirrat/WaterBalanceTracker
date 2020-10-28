package com.zefirratstudio.waterbalancetracker.ui.history

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

class HistoryFragment : Fragment() {
    private var galleryViewModel: HistoryViewModel? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        galleryViewModel = ViewModelProviders.of(this).get(HistoryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_history, container, false)
        val textView = root.findViewById<TextView?>(R.id.text_history)
        galleryViewModel.getText().observe(viewLifecycleOwner, Observer { s -> textView.setText(s) })
        return root
    }
}