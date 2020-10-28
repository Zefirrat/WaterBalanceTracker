package com.zefirratstudio.waterbalancetracker.database.models

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.util.*

class DrinkedWater {
    var _id:Long
    var Amount:Int=0
    @RequiresApi(Build.VERSION_CODES.O)
    var DateTime: LocalDateTime? = LocalDateTime.now()

    fun DrinkedWater(amount:Int, dateTime:LocalDateTime){
        Amount=amount
        DateTime = dateTime
    }
}