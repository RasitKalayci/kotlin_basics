package rasit.kalayci.retrofitweather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import rasit.kalayci.retrofitweather.api.NetworkResponse
import rasit.kalayci.retrofitweather.api.WeatherModel

@Composable
fun WeatherPage(viewmodel: WeatherViewModel){
    var city by remember {
        mutableStateOf("")
    }
    val weatherResult = viewmodel.weatherResult.observeAsState()

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
         Row(modifier = Modifier
             .fillMaxWidth()
             .padding(32.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
         ){
             TextField(modifier = Modifier.weight(1f),
                 value = city,
                 onValueChange = {city = it},
                 label = {
                     Text("Search for a city")

                 }
             )

             IconButton(onClick = {
                 viewmodel.getData(city)
             }) {
                 Icon(imageVector = Icons.Default.Search, contentDescription = "search for any location")
             }


        }

        when(val result = weatherResult.value){
            is NetworkResponse.Error -> {
                Text(text = "Error: ${result.message}")
            }
            NetworkResponse.Loading -> {
                CircularProgressIndicator()
            }
            is NetworkResponse.Success<*> -> {
                WeatherDetails(data = result.data as WeatherModel)
            }
            null -> {}
        }
    }
}


@Composable
fun WeatherDetails(data: WeatherModel){
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally


    ) {
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Start

            ) {
            Icon(imageVector = Icons.Default.Place, contentDescription = "location icon",
                modifier = Modifier.size(40.dp)
            )
            Text(text = data.name, fontSize = 30.sp)
            Text(text = data.sys.country, fontSize = 18.sp, color = Color.Gray)

        }
        Spacer(modifier = Modifier.padding(16.dp))
        Text(text = "${data.main.temp.toInt()}°C", fontSize = 64.sp, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.padding(16.dp))
        AsyncImage(
            model = "https://openweathermap.org/img/wn/${data.weather[0].icon}@2x.png",
            contentDescription = "weather icon",
            modifier = Modifier.size(128.dp)
        )
        Text(text = "${data.weather[0].description}", fontSize = 32.sp)

        Spacer(modifier = Modifier.padding(16.dp))
        Card() {
            Column(modifier = Modifier.fillMaxWidth()) {

                Row(modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    WeatherKey("Humidity", "${data.main.humidity}%")
                    WeatherKey("Pressure", "${data.main.pressure}hPa")




                }
                Row(modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    WeatherKey("Feels Like", "${data.main.feelsLike.toInt()}°C")
                    WeatherKey("Min Temp", "${data.main.tempMin.toInt()}°C")




                }

            }
        }

    }


}
@Composable
fun WeatherKey(key:Any, value: Any){
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = key.toString(), fontSize = 20.sp)
        Text(text = value.toString(), fontSize = 18.sp, color = Color.Gray)}
}