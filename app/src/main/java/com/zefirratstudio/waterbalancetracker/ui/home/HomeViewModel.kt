package com.zefirratstudio.waterbalancetracker.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zefirratstudio.waterbalancetracker.globalsettings.SettingsSingleton
import kotlinx.android.synthetic.main.fragment_home.*

class HomeViewModel : ViewModel() {
    private val mText: MutableLiveData<String?>?
    fun getText(): LiveData<String?>? {
        return mText
    }

    init {
        mText = MutableLiveData()
        _subscribeValues()
    }

    public fun OnViewCreated() {
        _refreshValues()
    }

    private fun _subscribeValues() {
        SettingsSingleton.getInstance()?.DataBaseController?.RefreshListListeners?.add { _refreshValues() }
        _set_text_home()
    }

    private fun _refreshValues() {
        _set_text_home()
    }

    private fun _set_text_home() {
        mText?.setValue(SettingsSingleton.getInstance()?.DataBaseController?.TodayAmount.toString())
    }
}