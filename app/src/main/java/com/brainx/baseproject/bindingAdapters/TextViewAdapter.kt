package com.brainx.baseproject.bindingAdapters

import android.widget.TextView
import androidx.databinding.BindingAdapter


@BindingAdapter(value = ["userName"], requireAll = true)
fun setUserName(
    view: TextView,
    userName: String?
) {
    view.text = "Mr. " + (userName ?: "").replace(" ", "-")
}
