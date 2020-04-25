package com.sunnyweather.android.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.sunnyweather.android.logic.Repository
import com.sunnyweather.android.logic.model.Place
import java.util.ArrayList

class PlaceViewModel : ViewModel() {

    private val searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<Place>()

    //switchMap 监听 searchLiveData 数据的变化，当数据改变时，调用 Repository.serachPlaces(query) 方法获取新的数据
    val placeLiveData = Transformations.switchMap(searchLiveData) {query ->
        Repository.serachPlaces(query)
    }

    // 定义一个方法 用来改变 serchLiveData
    fun serachPlaces(query: String) {
        searchLiveData.value = query
    }

    fun savePlace(place: Place) = Repository.savePlace(place)

    fun getSavedPlace() = Repository.getSavedPlace()

    fun isPlaceSaved() = Repository.isPlaceSaved()

}