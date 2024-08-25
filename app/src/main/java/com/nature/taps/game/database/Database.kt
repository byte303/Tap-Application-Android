package com.nature.taps.game.database

import com.nature.taps.game.R
import com.nature.taps.game.model.Improve
import io.paperdb.Paper

object Database {

    fun onGetImprove() = Paper.book().read("onGetImprove", onListImprove())!!
    fun onSetImprove(select : ArrayList<Improve>) = Paper.book().write("onGetImprove", select)

    private fun onListImprove() : ArrayList<Improve>{
        val data = ArrayList<Improve>()
        data.add(Improve("Энергия природы", R.drawable.image_1, 100,1000))
        data.add(Improve("Сила отдыха", R.drawable.image_2, 70,1000))
        data.add(Improve("Выносливость", R.drawable.image_3, 240,2000))
        data.add(Improve("Инстинкт", R.drawable.image_4, 80,550))
        data.add(Improve("Источник жизни", R.drawable.image_5, 40,350))
        data.add(Improve("Энергия зверя", R.drawable.image_6, 300,2000))
        data.add(Improve("Лесной клад", R.drawable.image_7, 320,2500))
        data.add(Improve("Баланс хищника", R.drawable.image_8, 100,1000))
        data.add(Improve("Скорость", R.drawable.image_9, 200,1670))
        data.add(Improve("Сладкая добыча", R.drawable.image_10, 400,3000))
        data.add(Improve("Цикл насыщения", R.drawable.image_11, 90,800))
        return data
    }


    fun onGetPriceBoost() = Paper.book().read("onGetPriceBoost", 3000)!!
    fun onSetPriceBoost(select : Int) = Paper.book().write("onGetPriceBoost", select)

    fun onGetPriceEnergy() = Paper.book().read("onGetPriceEnergy", 2000)!!
    fun onSetPriceEnergy(select : Int) = Paper.book().write("onGetPriceEnergy", select)


    fun onGetEnergy() = Paper.book().read("onGetEnergy", 100)!!
    fun onSetEnergy(select : Int) = Paper.book().write("onGetEnergy", select)

    fun onGetMaxEnergy() = Paper.book().read("onGetMaxEnergy", 100)!!
    fun onSetMaxEnergy(select : Int) = Paper.book().write("onGetMaxEnergy", select)

    fun onGetCoin() = Paper.book().read("COUNT", 0)!!
    fun onSetCoin(select : Int) = Paper.book().write("COUNT", select)

    fun onGetClickCoin() = Paper.book().read("onGetClickCoin", 1)!!
    fun onSetClickCoin(select : Int) = Paper.book().write("onGetClickCoin", select)

    fun onGetHourCoin() = Paper.book().read("onGetHourCoin", 0)!!
    fun onSetHourCoin(select : Int) = Paper.book().write("onGetHourCoin", select)

    fun onGetSelectAvatar() = Paper.book().read("AVATAR", 1)!!
    fun onSetSelectAvatar(select : Int) = Paper.book().write("AVATAR", select)

    fun onGetBuy() = Paper.book().read("onGetBuy", arrayListOf(true,true,false,false,false,false,false))!!
    fun onSetBuy(select : ArrayList<Boolean>) = Paper.book().write("onGetBuy", select)
}