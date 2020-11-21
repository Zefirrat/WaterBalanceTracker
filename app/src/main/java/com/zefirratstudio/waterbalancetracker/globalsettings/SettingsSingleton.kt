package com.zefirratstudio.waterbalancetracker.globalsettings

import com.zefirratstudio.waterbalancetracker.MainActivity
import com.zefirratstudio.waterbalancetracker.database.DataBaseController


class SettingsSingleton {
    var DailyNorm = 0.0
    lateinit var DataBaseController: DataBaseController

    companion object {
        @Volatile
        private var instance: SettingsSingleton? = null
        fun getInstance(): SettingsSingleton? {
            var localInstance = instance
            if (localInstance == null) {
                synchronized(SettingsSingleton::class.java) {
                    localInstance = instance
                    if (localInstance == null) {
                        localInstance = SettingsSingleton()
                        instance = localInstance
                    }
                }
            }
            return localInstance
        }
    }
}