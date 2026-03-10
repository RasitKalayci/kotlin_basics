package rasit.kalayci.wheatherapplication.data

import rasit.kalayci.wheatherapplication.models.City
import rasit.kalayci.wheatherapplication.models.ForecastItem
import rasit.kalayci.wheatherapplication.models.Weather

class WeatherRepo(private val api: WeatherApi,
    private val apiKey: String) {


    suspend fun getCurrentWeather(city: City): Weather {
        val dto = api.getCurrentWeather(
            lat = city.lat,
            lon = city.lon,
            apiKey = apiKey,
            units = "metric"
        )
        return dto.toWeatherUi()


    }
    suspend fun getForecast(city: City, maxItems: Int = 12): List<ForecastItem> {
        val dto = api.getForecast(
            lat = city.lat,
            lon = city.lon,
            apiKey = apiKey,
            units = "metric"
        )
        return dto.toForecastItems(maxItems)

    }
    suspend fun searchCities(query: String,limit: Int = 10): List<City> {
        val dtos = api.searchCities(
            query = query,
            limit = limit,
            apiKey = apiKey
        )
        return dtos.map { it.toCity()}

    }

}