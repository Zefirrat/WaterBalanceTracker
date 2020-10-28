package com.zefirratstudio.waterbalancetracker.database

import java.util.*

interface IDataBaseHelper {
    fun GetTodayAmount(): Int
    fun GetHistory(): Map<Date, Int>
    fun AddDrinkedWater(amount: Int)
}