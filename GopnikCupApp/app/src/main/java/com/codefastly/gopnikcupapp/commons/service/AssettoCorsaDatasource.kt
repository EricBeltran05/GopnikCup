package com.codefastly.gopnikcupapp.commons.service

import com.codefastly.gopnikcupapp.commons.service.dataclass.Session
import retrofit2.Call
import retrofit2.http.GET

interface AssettoCorsaDatasource {
    @GET("live")
    fun getLiveData(): Call<LiveDataResponse>
}

data class LiveDataResponse(
    val session: Session,
    val cars: List<Car>
)

data class Session(
    val type: String,
    val elapsedTime: Int,
    val remainingTime: Int
)

data class Car(
    val carId: Int,
    val driverName: String,
    val carModel: String,
    val currentLapTime: Float,
    val bestLapTime: Float,
    val position: Int
)