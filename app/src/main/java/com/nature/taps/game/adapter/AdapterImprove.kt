package com.nature.taps.game.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nature.taps.game.databinding.ListImproveBinding
import com.nature.taps.game.model.Improve
import java.util.Locale

class AdapterImprove(val listener : IAdapterImprove) : ListAdapter<Improve, AdapterImprove.ItemHolder>(ItemComparator()) {

    interface IAdapterImprove{
        fun onAdapterImprove(item : Improve)
    }

    private lateinit var binding : ListImproveBinding

    inner class ItemHolder(private val binding: ListImproveBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Improve, position : Int) =
            with(binding){

                imageView4.setImageResource(item.icon)
                textView2.text = item.title
                txtBoost.text = String.format(Locale.getDefault(),"+ %d", item.boost)
                txtPrice.text = item.price.toString()

                container.setOnClickListener {
                    listener.onAdapterImprove(item)
                }
        }
    }

    class ItemComparator : DiffUtil.ItemCallback<Improve>(){
        override fun areItemsTheSame(oldItem: Improve, newItem: Improve): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Improve, newItem: Improve): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ListImproveBinding.inflate(inflater, parent, false)
        return ItemHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(getItem(position), position)
    }
}