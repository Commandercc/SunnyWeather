package com.sunnyweather.android.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import retrofit2.http.Query
import java.lang.Appendable
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object SunnyWeatherNetwork {

    private val placeService = ServiceCreator.create<PlaceService>()

    private val weatherService = ServiceCreator.create<WeatherService>()

    suspend fun getDailyWeather(lng: String,lat: String) = weatherService.getDailyWeather(lng,lat).await()

    suspend fun getRealtimeWeather(lng: String,lat: String) = weatherService.getRealtimeWeather(lng, lat).await()

    suspend fun searchPlaces(query: String) = placeService.searchPlaecs(query).await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if(body != null) {
                        continuation.resume(body)  //调用resume方法恢复被挂起的协程并传入服务器返回的数据，该数据即为上面suspendCoroutine函数的返回值
                    }else {
                        continuation.resumeWithException(RuntimeException("response body is null"))
                    }
                }

            })
        }
    }
}