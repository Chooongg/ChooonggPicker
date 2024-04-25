package com.chooongg.picker.app

import android.content.Context
import androidx.annotation.Keep
import androidx.startup.Initializer

@Keep
@Suppress("unused")
class FlipperInitializer : Initializer<String> {
    override fun create(context: Context): String {
        return "Flipper is keep"
    }

    override fun dependencies() = mutableListOf<Class<out Initializer<*>>>()
}