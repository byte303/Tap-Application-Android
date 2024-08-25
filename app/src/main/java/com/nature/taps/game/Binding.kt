package com.nature.taps.game

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.google.android.material.card.MaterialCardView

object Binding {
    @BindingAdapter("app:strokeWidth")
    @JvmStatic
    fun setStrokeWidth(view: MaterialCardView, strokeWidth: Float) {
        view.strokeWidth  = strokeWidth.toInt() // Пример установки ширины обводки, если используется обводка в виде тени
    }

    @BindingAdapter("imageResource")
    @JvmStatic
    fun setImageResource(imageView: ImageView, @DrawableRes resource: Int) {
        imageView.setImageResource(resource)
    }
}