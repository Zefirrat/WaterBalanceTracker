package com.zefirratstudio.waterbalancetracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DatabaseName = "WaterBalanceTracker.db";

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TrackedWaterTableWrapper.TableName
                + " (" + TrackedWaterTableWrapper.ID + " integer primary key autoincrement, "
                + TrackedWaterTableWrapper.AmountOfWater + " integer, "
                + TrackedWaterTableWrapper.DrinkDateTime + " datetime)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TrackedWaterTableWrapper.TableName);
        onCreate(db);
    }
}
