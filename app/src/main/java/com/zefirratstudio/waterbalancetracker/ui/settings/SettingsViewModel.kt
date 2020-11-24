package com.zefirratstudio.waterbalancetracker.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zefirratstudio.waterbalancetracker.globalsettings.SettingsSingleton

class SettingsViewModel : ViewModel() {

    public fun ChangeDailyNorm(amount: Int) {
        SettingsSingleton.getInstance()?.DataBaseController?.SetDailyNorm(amount)
    }


}