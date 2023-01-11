package com.fackerdev.cupones.common.adapters

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isGone")
fun isGone(view:View,isGone:Boolean){
    view.visibility = if (isGone) View.GONE else View.VISIBLE
}