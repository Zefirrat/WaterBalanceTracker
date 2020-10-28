package com.zefirratstudio.waterbalancetracker.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.zefirratstudio.waterbalancetracker.TrackedWaterTableWrapper
import com.zefirratstudio.waterbalancetracker.database.models.DrinkedWater
import nl.qbusict.cupboard.CupboardFactory.cupboard
import java.util.*


class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "WaterBalanceTracker.db", null, 1), IDataBaseHelper {

    private val DATABASE_NAME = "WaterBalanceTracker.db"
    private val DATABASE_VERSION = 1

    init {
        configureCupboard {
            register<Book>()
        }
    }

    override fun onConfigure(db: SQLiteDatabase?) {
        super.onConfigure(db)
        db?.setForeignKeyConstraintsEnabled(true)
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table " + TrackedWaterTableWrapper.TableName
                + " (" + TrackedWaterTableWrapper.ID + " integer primary key autoincrement, "
                + TrackedWaterTableWrapper.AmountOfWater + " integer, "
                + TrackedWaterTableWrapper.DrinkDateTime + " datetime)")

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion != newVersion) {
            db.execSQL("drop table if exists " + TrackedWaterTableWrapper.TableName)
            onCreate(db)
        }
    }

    override fun GetTodayAmount(): Int {
        writableDatabase?.execSQL("select * from ${TrackedWaterTableWrapper.TableName}")
        return 0
    }

    override fun GetHistory(): Map<Date, Int> {
        TODO("Not yet implemented")
    }

    override fun AddDrinkedWater(amount: Int) {
        TODO("Not yet implemented")
    }
}