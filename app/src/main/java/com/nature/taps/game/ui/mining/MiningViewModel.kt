package com.nature.taps.game.ui.mining

import android.util.Log
import android.view.View
import android.widget.Adapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.nature.taps.game.R
import com.nature.taps.game.adapter.AdapterImprove
import com.nature.taps.game.database.Database
import com.nature.taps.game.database.Database.onGetBuy
import com.nature.taps.game.database.Database.onGetClickCoin
import com.nature.taps.game.database.Database.onGetCoin
import com.nature.taps.game.database.Database.onGetEnergy
import com.nature.taps.game.database.Database.onGetHourCoin
import com.nature.taps.game.database.Database.onGetImprove
import com.nature.taps.game.database.Database.onGetMaxEnergy
import com.nature.taps.game.database.Database.onSetBuy
import com.nature.taps.game.database.Database.onSetClickCoin
import com.nature.taps.game.database.Database.onSetCoin
import com.nature.taps.game.database.Database.onSetEnergy
import com.nature.taps.game.database.Database.onSetHourCoin
import com.nature.taps.game.database.Database.onSetImprove
import com.nature.taps.game.database.Database.onSetSelectAvatar
import com.nature.taps.game.model.Improve

class MiningViewModel : ViewModel(), AdapterImprove.IAdapterImprove {

    val coin : MutableLiveData<Int> = MutableLiveData<Int>(onGetCoin())

    val adapter : MutableLiveData<AdapterImprove> = MutableLiveData<AdapterImprove>(AdapterImprove(this))

    val energy : MutableLiveData<Int> = MutableLiveData<Int>(onGetEnergy())
    val maxEnergy : MutableLiveData<Int> = MutableLiveData<Int>(onGetMaxEnergy())
    val moneyHour : MutableLiveData<Int> = MutableLiveData<Int>(onGetHourCoin())

    val getMyLevel : MutableLiveData<Int> = MutableLiveData<Int>(onGetLevel(coin.value!!))
    val getMyLevelCount : MutableLiveData<Int> = MutableLiveData<Int>(onGetLevel(coin.value!!))
    val nextLevelText = arrayListOf(
       "1 0000","10 000","50 000","100 000","500 000","1 000 000","5 000 000","10 000 000","50 000 000","100 000 000","+10 0000 000"
    )
    val levelCount = arrayListOf(
        10000,10000,50000,100000,500000,1000000,5000000,10000000,50000000,100000000,100000000
    )

    init {
        adapter.value!!.submitList(onGetImprove())
    }

    private fun onGetLevel(price: Int): Int {
        return when (price) {
            in 0..10000 -> 1
            in 10001..50000 -> 2
            in 50001..100000 -> 3
            in 100001..500000 -> 4
            in 500001..1000000 -> 5
            in 1000001..5000000 -> 6
            in 5000001..10000000 -> 7
            in 10000001..50000000 -> 8
            in 50000001..100000000 -> 9
            else -> 10
        }
    }

    fun onTap(view : View){
        if(energy.value!! > 0){
            energy.value = energy.value!!-1
            onSetEnergy(energy.value!!)

            coin.value = coin.value!! + onGetClickCoin()
            onSetCoin(coin.value!!)
            coin.postValue(onGetCoin())

            getMyLevel.postValue(onGetLevel(coin.value!!))
        }
    }

    fun onUpdateCoin(){
        coin.postValue(onGetCoin())
        energy.postValue(onGetEnergy())
        maxEnergy.postValue(onGetMaxEnergy())
        moneyHour.postValue(onGetHourCoin())
    }
    private val _indexItem = MutableLiveData<Int>().apply {
        value = Database.onGetSelectAvatar()
    }
    val indexItem: LiveData<Int> get() = _indexItem


    private val _indexAvatar = MutableLiveData<Int>().apply {
        value = Database.onGetSelectAvatar()
    }
    val indexAvatar: LiveData<Int> get() = _indexAvatar
    // Transformations.map для автоматического обновления ресурса аватара
    val avatarResource: LiveData<Int> = indexAvatar.map { index ->
        when (index) {
            1 -> R.drawable.avatar1
            2 -> R.drawable.avatar2
            3 -> R.drawable.avatar3
            4 -> R.drawable.avatar4
            5 -> R.drawable.avatar5
            6 -> R.drawable.avatar6
            else -> R.drawable.avatar1
        }
    }
    val avatarResourceSelect: LiveData<Int> = indexItem.map { index ->
        when (index) {
            1 -> R.drawable.avatar1
            2 -> R.drawable.avatar2
            3 -> R.drawable.avatar3
            4 -> R.drawable.avatar4
            5 -> R.drawable.avatar5
            6 -> R.drawable.avatar6
            else -> R.drawable.avatar1
        }
    }

    // Обработка клика
    fun onClick(view: View) {

        val tag = view.tag.toString().toInt()
        _indexItem.value = tag
        Log.d("onClick",tag.toString())

        isBuy.postValue(onGetBuy()[tag])
    }

    fun onClickBuy(view : View){
        val tag = indexItem.value?.toInt()
        if(onGetCoin() >= price[tag!!]){
            val getBut = onGetBuy()
            getBut[tag] = true
            onSetBuy(getBut)

            var myPrice = onGetCoin()
            myPrice -= price[tag]
            onSetCoin(myPrice)

            isBuy.postValue(onGetBuy()[tag])
            _indexAvatar.postValue(tag)
            onSetSelectAvatar(tag)
        }
    }

    val isBuy : MutableLiveData<Boolean> = MutableLiveData<Boolean>(true)
    val textPrice = arrayListOf(
        "0","0","10 000","100 000","300 000","800 000","1 000 000"
    )
    val price = arrayListOf(
        0,0,10000,100000,300000,800000,1000000
    )

    override fun onAdapterImprove(item: Improve) {
        if(onGetCoin() >= item.price){
            val list = onGetImprove()
            val myItem = list.indexOfFirst { it.title == item.title }
            list[myItem].boost += 100
            list[myItem].price *= 3
            onSetImprove(list)
            adapter.value!!.submitList(onGetImprove())

            var myPrice = onGetCoin()
            myPrice -= item.price
            onSetCoin(myPrice)

            var hourMoney = onGetHourCoin()
            hourMoney += item.boost
            onSetHourCoin(hourMoney)
            moneyHour.postValue(onGetHourCoin())
        }
    }

}