package rasit.kalayci.retrofitweather.api

import com.google.gson.annotations.SerializedName

// Root ismini WeatherModel olarak değiştirdik
data class WeatherModel(
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Long,
    val wind: Wind,
    val rain: Rain? = null,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val timezone: Long,
    val id: Long,
    val name: String,
    val cod: Long,
)

data class Coord(
    val lon: Double,
    val lat: Double,
)

data class Weather(
    val id: Long,
    val main: String,
    val description: String,
    val icon: String,
)

data class Main(
    val temp: Double,
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("temp_min")
    val tempMin: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    val pressure: Long,
    val humidity: Long,
    @SerializedName("sea_level")
    val seaLevel: Long? = null,
    @SerializedName("grnd_level")
    val grndLevel: Long? = null,
)

data class Wind(
    val speed: Double,
    val deg: Long,
    val gust: Double? = null,
)

data class Rain(
    @SerializedName("1h")
    val n1h: Double? = null,
)

data class Clouds(
    val all: Long,
)

data class Sys(
    val type: Long? = null,
    val id: Long? = null,
    val country: String,
    val sunrise: Long,
    val sunset: Long,
)
