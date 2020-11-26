package com.zefirratstudio.waterbalancetracker.database

import android.database.Observable
import com.zefirratstudio.waterbalancetracker.R
import com.zefirratstudio.waterbalancetracker.database.models.DailyNorm
import java.util.*
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class DataBaseController(dbHelper: IDataBaseHelper) {
    var TodayAmount: Int by Delegates.observable(0) { _, oldValie, newValue ->
        RefreshListListeners.forEach {
            it()
        }
    }

    var RefreshListListeners = ArrayList<() -> Unit>()

    var History: Map<Date, Int> by Delegates.observable(emptyMap()) { _, oldValue, newValue ->
        RefreshListListeners.forEach {
            it()
        }
    }
    var DailyNorm: Int by Delegates.observable(1) { _, oldValue, newValue -> RefreshListListeners.forEach { it() } }

    private var _dbHelper: IDataBaseHelper = dbHelper

    init {
        _refreshValues()
    }

    fun AddDrinkedWater(amount: Int) {
        _dbHelper.AddDrinkedWater(amount)
        _refreshValues()
    }

    fun SetDailyNorm(amount: Int) {
        _dbHelper.SetDailyNorm(amount)
    }

    public fun RefreshValues() {
        _refreshValues()
    }

    private fun _refreshValues() {
        TodayAmount = _dbHelper.GetTodayAmount()
        History = _dbHelper.GetHistory()
        var dn = _dbHelper.GetDailyNorm()
        if (dn < 1)
            dn = 1
        DailyNorm = dn
    }
}