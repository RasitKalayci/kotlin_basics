package rasit.kalayci.wheatherapplication.models

data class City(val id: String,val name: String ,val lat: Double,val lon: Double){

}
data class Weather(val temp: Double,val feelsLike: Double,val icon : String,val windSpeed : Double )

data class ForecastItem(val time : Long,val temp : Double,val icon : String)