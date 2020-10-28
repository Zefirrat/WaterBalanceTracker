package com.zefirratstudio.waterbalancetracker

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.zefirratstudio.waterbalancetracker.WellKnownTypes.HoldingCapacityWellKnownType
import com.zefirratstudio.waterbalancetracker.database.DataBaseController
import com.zefirratstudio.waterbalancetracker.database.DatabaseHelper

class MainActivity : AppCompatActivity() {
    private var mAppBarConfiguration: AppBarConfiguration? = null
    private var _fab1: FloatingActionButton? = null
    private var _fab2: FloatingActionButton? = null
    private var _fab3: FloatingActionButton? = null
    private var _fab4: FloatingActionButton? = null
    private var _isFABOpen = false
    private var _todayAmountLabel: TextView? = null
    private var _dbController: DataBaseController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_history, R.id.nav_settings)
                .setDrawerLayout(drawer)
                .build()
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration!!)
        NavigationUI.setupWithNavController(navigationView, navController)
        _subscribeFABs()
        _loadDatabase()
        _subscribeValues()
    }

    private fun _subscribeValues() {
        _dbController?.onTodayAmountChanged = { s: String, s1: String ->_refreshValues()}

        _todayAmountLabel = findViewById<View>(R.id.text_home) as TextView
        _todayAmountLabel!!.text = _dbController?.TodayAmount.toString()
    }

    private fun _loadDatabase() {
        val dbHelper = DatabaseHelper(null, null, null, 0)
        _dbController = DataBaseController(dbHelper)
    }

    private fun _refreshValues() {
        _todayAmountLabel!!.text = _dbController?.TodayAmount.toString()
    }

    private fun _subscribeFABs() {
        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        _fab1 = findViewById<View>(R.id.fab1) as FloatingActionButton
        _fab2 = findViewById<View>(R.id.fab2) as FloatingActionButton
        _fab3 = findViewById<View>(R.id.fab3) as FloatingActionButton
        _fab4 = findViewById<View>(R.id.fab4) as FloatingActionButton
        fab.setOnClickListener {
            if (!_isFABOpen) {
                _showFABMenu()
            } else {
                _closeFABMenu()
            }
        }
        _fab1!!.setOnClickListener {
            _dbController?.AddDrinkedWater(HoldingCapacityWellKnownType.Glass.toInt())
        }
        _fab2!!.setOnClickListener {
            _dbController?.AddDrinkedWater(HoldingCapacityWellKnownType.Cup.toInt())
        }
        _fab3!!.setOnClickListener {
            _dbController?.AddDrinkedWater(HoldingCapacityWellKnownType.BigGlass.toInt())
        }
        _fab4!!.setOnClickListener {
            _dbController?.AddDrinkedWater(HoldingCapacityWellKnownType.BigPlastic.toInt())
        }
    }

    private fun _showFABMenu() {
        _isFABOpen = true
        _fab1!!.animate().translationY(-resources.getDimension(R.dimen.standard_55))
        _fab2!!.animate().translationY(-resources.getDimension(R.dimen.standard_105))
        _fab3!!.animate().translationY(-resources.getDimension(R.dimen.standard_155))
        _fab4!!.animate().translationY(-resources.getDimension(R.dimen.standart_205))
    }

    private fun _closeFABMenu() {
        _isFABOpen = false
        _fab1!!.animate().translationY(0f)
        _fab2!!.animate().translationY(0f)
        _fab3!!.animate().translationY(0f)
        _fab4!!.animate().translationY(0f)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        return (NavigationUI.navigateUp(navController, mAppBarConfiguration!!)
                || super.onSupportNavigateUp())
    }
}