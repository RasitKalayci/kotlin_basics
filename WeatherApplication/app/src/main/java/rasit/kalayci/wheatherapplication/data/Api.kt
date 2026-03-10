package rasit.kalayci.wheatherapplication.data

import okhttp3.OkHttpClient
import rasit.kalayci.wheatherapplication.models.City
import rasit.kalayci.wheatherapplication.models.ForecastItem
import rasit.kalayci.wheatherapplication.models.Weather
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object Api {
    private const val baseUrl = "https://api.openweathermap.org/"


    private val okHtttp = OkHttpClient.Builder()
        .build()

    val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHtttp)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val weatherApi: WeatherApi = retrofit.create(WeatherApi::class.java)

}
interface WeatherApi {
    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ):CurrentWeatherDto
    @GET("data/2.5/forecast")
    suspend fun getForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ):ForecastDto
    @GET("geo/1.0/direct")
    suspend fun searchCities(
        @Query("q") query: String,
        @Query("limit") limit: Int = 10,
        @Query("appid") apiKey: String
    ):List<CityDto>
}
data class CurrentWeatherDto(
val weather: List<WeatherDescDto>,
    val main: MainDto,
    val wind: WindDto,

)
data class WeatherDescDto(
    val description: String,
    val icon: String
)
data class MainDto(
    val temp: Double,
    val feels_like: Double
)
data class WindDto(
    val speed: Double
)
data class ForecastDto(
    val list: List<ForecastListItemDto>
)
data class ForecastListItemDto(
    val dt: Long,
    val main: ForecastMainDto,
    val weather: List<WeatherDescDto>
)
data class  ForecastMainDto(
    val temp: Double)

data class CityDto(
    val name: String,
    val lat: Double,
    val lon: Double,
    val country: String?= null
)
fun CityDto.toCity(): City {
    val id = "${lat}_$lon"
val displayName  = if (country.isNullOrBlank()) name else "$name, $country"
    return City(
        id = id,
        name = displayName,
        lat = lat,
        lon = lon
    )
}

fun CurrentWeatherDto.toWeatherUi(): Weather {
    val w = weather.firstOrNull()
    return Weather(
        temp = main.temp,
        feelsLike = main.feels_like,
        icon = w?.icon ?: "",
        windSpeed = wind.speed
    )
}

fun ForecastDto.toForecastItems(maxItems: Int = 12): List<ForecastItem> {
    // maxItems: UI'da çok uzamasın diye (örn 12 tane = 36 saat)
    return list.take(maxItems).map { item ->
        val w = item.weather.firstOrNull()
        ForecastItem(
            time = item.dt,           // epoch seconds
            temp = item.main.temp,
            icon = w?.icon ?: ""
        )
    }
}
