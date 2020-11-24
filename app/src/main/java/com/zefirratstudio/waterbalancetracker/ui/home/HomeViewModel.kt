package com.zefirratstudio.waterbalancetracker.ui.home

import android.R
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zefirratstudio.waterbalancetracker.globalsettings.SettingsSingleton


class HomeViewModel : ViewModel() {
    private val mText: MutableLiveData<String?>?
    fun getText(): LiveData<String?>? {
        return mText
    }

    init {
        mText = MutableLiveData()
        _subscribeValues()
    }

    public var DailyAmountView: View? = null

    public var DisplayHeight: Int = 0
    public fun OnViewCreated() {
        _refreshValues()
    }

    private fun _subscribeValues() {
        SettingsSingleton.getInstance()?.DataBaseController?.RefreshListListeners?.add { _refreshValues() }
        _set_text_home()
    }

    private fun _refreshValues() {
        _set_text_home()
        _set_height_of_view()
    }

    private fun _set_height_of_view() {
        if (DailyAmountView != null) {
            val todayAmount = SettingsSingleton.getInstance()?.DataBaseController?.TodayAmount
            val dailyAmount = SettingsSingleton.getInstance()?.DataBaseController?.DailyNorm
            if (todayAmount != null) {
//                DailyAmountView!!.layoutParams = ConstraintLayout.LayoutParams(DailyAmountView!!.width.toInt(), (DisplayHeight * 0.75 * (todayAmount / dailyAmount!!)).toInt())
//                DailyAmountView!!.layoutParams.
                DailyAmountView!!.layoutParams.height = (DisplayHeight * 0.75 * (todayAmount / dailyAmount!!)).toInt()

                DailyAmountView!!.invalidate()
            }
        }
    }

    private fun _set_text_home() {
        mText?.setValue(SettingsSingleton.getInstance()?.DataBaseController?.TodayAmount.toString())
    }
}