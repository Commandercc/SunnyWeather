package com.sunnyweather.android.logic.dao

import android.content.Context
import androidx.core.content.edit

import com.google.gson.Gson
import com.sunnyweather.SunnyWeatherApplication
import com.sunnyweather.android.logic.model.Place

object PlaceDao {

    private fun sharedPreferences() = SunnyWeatherApplication.context.getSharedPreferences("sunny_weather",Context.MODE_PRIVATE)

    fun savePlace(place: Place) {
        sharedPreferences().edit{
            putString("place",Gson().toJson(place))  //将place转成json字符串来存储
        }
    }

    fun getSavedPlace(): Place {
        val placeJson = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeJson,Place::class.java) //将json字符串解析成Place对象
    }

    fun isPlaceSaved() = sharedPreferences().contains("place")

}