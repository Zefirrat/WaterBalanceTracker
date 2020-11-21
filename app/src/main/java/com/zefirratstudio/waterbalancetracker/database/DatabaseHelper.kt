package com.zefirratstudio.waterbalancetracker.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi
import com.jskierbi.cupboard.configureCupboard
import com.jskierbi.cupboard.query
import com.jskierbi.cupboard.register
import com.zefirratstudio.waterbalancetracker.database.models.DrinkedWater
import nl.qbusict.cupboard.CupboardFactory.cupboard
import java.util.*


class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "WaterBalanceTracker.db", null, 2), IDataBaseHelper {

    private val DATABASE_NAME = "WaterBalanceTracker.db"
    private val DATABASE_VERSION = 2

    init {
        configureCupboard {
            register<DrinkedWater>()
        }
    }

    override fun onConfigure(db: SQLiteDatabase?) {
        super.onConfigure(db)
        db?.setForeignKeyConstraintsEnabled(true)
    }

    override fun onCreate(db: SQLiteDatabase) {
        cupboard().withDatabase(db).createTables()

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        cupboard().withDatabase(db).upgradeTables()
    }

    override fun GetTodayAmount(): Int {

        // today
        val date: Calendar = GregorianCalendar()
// reset hour, minutes, seconds and millis
// reset hour, minutes, seconds and millis
        date[Calendar.HOUR_OF_DAY] = 0
        date[Calendar.MINUTE] = 0
        date[Calendar.SECOND] = 0
        date[Calendar.MILLISECOND] = 0
        val list = cupboard().withDatabase(readableDatabase).query<DrinkedWater>().list()
        var amount = 0
        list.filter { it.Date.after(Date(date.timeInMillis)) }.forEach { amount += it.Amount }
        return amount
    }

    override fun GetHistory(): Map<Date, Int> {
        val list = cupboard().withDatabase(readableDatabase).query<DrinkedWater>().list()
        return list.map { it.Date to it.Amount }.toMap()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun AddDrinkedWater(amount: Int) {
        val drinkedWater = DrinkedWater(amount, Date())
        cupboard().withDatabase(writableDatabase).put(drinkedWater)
    }
}