package com.zefirratstudio.waterbalancetracker.globalsettings

import org.junit.runner.RunWith

class SettingsSingleton {
    var DailyNorm = 0.0

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