package com.nature.taps.game

import android.os.Bundle
import android.os.CountDownTimer
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.nature.taps.game.database.Database.onGetCoin
import com.nature.taps.game.database.Database.onGetEnergy
import com.nature.taps.game.database.Database.onGetHourCoin
import com.nature.taps.game.database.Database.onGetMaxEnergy
import com.nature.taps.game.database.Database.onSetCoin
import com.nature.taps.game.database.Database.onSetEnergy
import com.nature.taps.game.databinding.ActivityMainBinding
import com.nature.taps.game.ui.mining.MiningViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)[MiningViewModel::class.java]

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

        val timer = object: CountDownTimer(1000*60*60*24*10, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val hour = onGetHourCoin()/60/60
                val coin = onGetCoin()
                var energy = onGetEnergy()
                if(energy < onGetMaxEnergy()) {
                    energy++
                    onSetEnergy(energy)
                }
                onSetCoin(coin + hour)
                viewModel.onUpdateCoin()
            }

            override fun onFinish() {

            }
        }
        timer.start()
    }
}