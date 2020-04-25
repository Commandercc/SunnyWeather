package com.sunnyweather.android.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.sunnyweather.android.logic.Repository
import com.sunnyweather.android.logic.model.Location

class WeatherViewModel : ViewModel() {

    private val locationLiveData = MutableLiveData<Location>()

    var locationLng = ""

    var locationLat = ""

    var placeName = ""

    // switchMap 接收两个参数，第一个是要观察的对象,这里是locationLiveData;第二是转换函数,
    // 转换函数中的参数即为第一个参数包含的对象类型（所对应的泛型对象）,这里
    // 第一个参数类型为MutableLiveData<Location>() ,因此转换函数中的参数即为 Location 类型。
    // 并且switchMap 最终返回的也是一个LiveData<--> 对象
    val weatherLiveData = Transformations.switchMap(locationLiveData){ location ->
        Repository.refreshWeather(location.lng,location.lat)
    }

    fun refreshWeather(lng: String,lat: String) {
        locationLiveData.value = Location(lng,lat)
    }

}