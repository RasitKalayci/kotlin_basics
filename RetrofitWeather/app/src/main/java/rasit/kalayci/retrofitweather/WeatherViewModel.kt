package rasit.kalayci.retrofitweather

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import rasit.kalayci.retrofitweather.api.Constant
import rasit.kalayci.retrofitweather.api.NetworkResponse
import rasit.kalayci.retrofitweather.api.RetrofitInstance
import rasit.kalayci.retrofitweather.api.WeatherModel

class WeatherViewModel : ViewModel(){
    private val weatherapi= RetrofitInstance.weatherapi
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
    val weatherResult: MutableLiveData<NetworkResponse<WeatherModel>> = _weatherResult


    fun getData(city: String) {
        _weatherResult.value=(NetworkResponse.Loading)
        viewModelScope.launch {
           try {
               val response = weatherapi.getWeather(Constant.apiKey, city)
               if (response.isSuccessful) {
                   response.body()?.let {
                       _weatherResult.value=(NetworkResponse.Success(it))
                   }

               }else{
                   _weatherResult.value=(NetworkResponse.Error("failed to load data"))


               }
           }catch (e: Exception){
               _weatherResult.value=(NetworkResponse.Error("failed to load data"))
           }

        }
    }

        }



