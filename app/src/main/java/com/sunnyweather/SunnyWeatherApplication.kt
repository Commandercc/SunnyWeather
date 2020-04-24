package com.sunnyweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SunnyWeatherApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        const val TOKEN = "Ft2PkYlwts2YCV6t"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}