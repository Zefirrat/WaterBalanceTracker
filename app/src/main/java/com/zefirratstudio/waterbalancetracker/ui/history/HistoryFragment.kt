package com.zefirratstudio.waterbalancetracker.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.zefirratstudio.waterbalancetracker.MainActivity
import com.zefirratstudio.waterbalancetracker.R
import kotlinx.android.synthetic.main.fragment_history.*
import java.util.*
import kotlin.collections.ArrayList


class HistoryFragment : Fragment() {
    private var galleryViewModel: HistoryViewModel? = null

    private lateinit var listView: ListView

    private var listItems: ArrayList<String> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        galleryViewModel = ViewModelProviders.of(this).get(HistoryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_history, container, false)

        listView = root.findViewById(R.id.history_list_view)
        listItems.addAll(galleryViewModel!!.getList())
        val arrayAdapter = activity?.let { ArrayAdapter(it, android.R.layout.simple_list_item_1, listItems) }
        listView.adapter = arrayAdapter
        galleryViewModel!!.ListChangedCallback = { listChangedCallback() }
        return root
    }

    private fun listChangedCallback() {
        listItems.clear()
        listItems.addAll(galleryViewModel!!.getList())
        val arrayAdapter = listView.adapter as ArrayAdapter<String>
//        arrayAdapter?.clear()
//        arrayAdapter?.addAll(listItems)
        arrayAdapter?.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        galleryViewModel?.OnViewCreated()
    }
}