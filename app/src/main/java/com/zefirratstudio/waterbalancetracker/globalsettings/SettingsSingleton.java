package com.zefirratstudio.waterbalancetracker.globalsettings;

public final class SettingsSingleton {

    private static volatile SettingsSingleton instance;

    public static SettingsSingleton getInstance() {
        SettingsSingleton localInstance = instance;
        if (localInstance == null) {
            synchronized (SettingsSingleton.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new SettingsSingleton();
                }
            }
        }
        return localInstance;
    }

    public double DailyNorm;
}
