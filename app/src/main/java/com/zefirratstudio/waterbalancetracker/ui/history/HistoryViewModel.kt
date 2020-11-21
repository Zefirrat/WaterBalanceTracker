package com.zefirratstudio.waterbalancetracker.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zefirratstudio.waterbalancetracker.globalsettings.SettingsSingleton

class HistoryViewModel : ViewModel() {
    private val mList: ArrayList<String> = ArrayList()
    fun getList(): ArrayList<String> {
        return mList
    }

    init {
        _subscribeValues()
    }

    public fun OnViewCreated() {
        _refreshValues()
    }

    private fun _subscribeValues() {
        SettingsSingleton.getInstance()?.DataBaseController?.RefreshListListeners?.add { _refreshValues() }
        _set_list()
    }

    private fun _refreshValues() {
        _set_list()
    }

    private fun _set_list() {
        mList?.clear()
        SettingsSingleton.getInstance()?.DataBaseController?.History?.forEach {
            mList?.add(it.key.toString() + "\n" + it.value.toString())
        }
    }

    public lateinit var ListChangedCallback: () -> Unit
}