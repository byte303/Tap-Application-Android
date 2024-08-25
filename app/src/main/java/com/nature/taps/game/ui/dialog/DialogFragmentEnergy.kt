package com.nature.taps.game.ui.dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.nature.taps.game.R
import com.nature.taps.game.database.Database.onGetCoin
import com.nature.taps.game.database.Database.onGetEnergy
import com.nature.taps.game.database.Database.onGetPriceEnergy
import com.nature.taps.game.database.Database.onSetCoin
import com.nature.taps.game.database.Database.onSetEnergy
import com.nature.taps.game.database.Database.onSetPriceEnergy
import com.nature.taps.game.databinding.FragmentDialogEnergyBinding

class DialogFragmentEnergy : DialogFragment() {

    private lateinit var binding : FragmentDialogEnergyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDialogEnergyBinding.inflate(inflater, container, false)


        val price = onGetPriceEnergy()
        binding.txtPrice.text = price.toString()

        binding.btnBuy.setOnClickListener {
            if(onGetCoin() >= price){
                val money = onGetCoin()-price
                onSetCoin(money)

                val newPrice = price * 2
                onSetPriceEnergy(newPrice)

                val energy = onGetEnergy() + 500
                onSetEnergy(energy)

                setFragmentResult("RESULT",Bundle())
                dismiss()
            }
        }

        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            STYLE_NORMAL,
            R.style.MyDialogTheme
        )
    }
}