package com.zefirratstudio.waterbalancetracker.database

import android.database.Observable
import com.zefirratstudio.waterbalancetracker.R
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

    private var _dbHelper: IDataBaseHelper = dbHelper

    init {
        _refreshValues()
    }

    fun AddDrinkedWater(amount: Int) {
        _dbHelper.AddDrinkedWater(amount)
        _refreshValues()
    }

    private fun _refreshValues() {
        TodayAmount = _dbHelper.GetTodayAmount()
        History = _dbHelper.GetHistory()
    }
}