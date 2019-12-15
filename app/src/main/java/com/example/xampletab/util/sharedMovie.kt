package com.example.xampletab.util

import android.content.Intent
import com.google.gson.Gson
import com.google.gson.GsonBuilder


const val DEFAULT_NAME = "object"

object IntentUtil {
    @Suppress("SpellCheckingInspection")
    val gson: Gson = GsonBuilder().create()
}



fun Intent.putExtraJson(src: Any) {
    putExtra(DEFAULT_NAME, IntentUtil.gson.toJson(src))
}


fun <T> Intent.getJsonExtra(`class`: Class<T>): T? {
    val stringExtra = getStringExtra(DEFAULT_NAME)
    if (stringExtra != null) {
        return IntentUtil.gson.fromJson<T>(stringExtra, `class`)
    }
    return null
}