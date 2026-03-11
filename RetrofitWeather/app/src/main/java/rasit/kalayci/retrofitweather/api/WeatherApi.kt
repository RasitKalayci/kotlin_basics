package rasit.kalayci.retrofitweather.api

import rasit.kalayci.retrofitweather.WeatherViewModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/data/2.5/weather")
    suspend fun getWeather(
        @Query("appid") apiKey: String,
        @Query("q") city: String,
        @Query("units") units: String="metric"
    ): Response<WeatherModel>



}