package rasit.kalayci.wheatherapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import rasit.kalayci.wheatherapplication.data.Api
import rasit.kalayci.wheatherapplication.data.FavouritesRepo
import rasit.kalayci.wheatherapplication.data.WeatherRepo
import rasit.kalayci.wheatherapplication.data.room.AppDatabase
import rasit.kalayci.wheatherapplication.ui.theme.WheatherApplicationTheme
import rasit.kalayci.wheatherapplication.userinterface.AppNav
import rasit.kalayci.wheatherapplication.userinterface.FavoritesViewModel
import rasit.kalayci.wheatherapplication.userinterface.HomeViewModel
import rasit.kalayci.wheatherapplication.userinterface.SearchViewModel

class MainActivity : ComponentActivity() {
    // Şimdilik burada dursun. Sonra BuildConfig'e taşırız.
    private val API_KEY = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /* ------------------- ROOM ------------------- */
        val db: AppDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "weather_app.db"
        ).build()

        val dao = db.favouriteCityDao()

        /* ------------------- REPOS ------------------- */
        val favoritesRepo = FavouritesRepo(dao)
        val weatherRepo = WeatherRepo(Api.weatherApi, API_KEY)

        /* ------------------- VIEWMODELS (Factory ile) ------------------- */
        val homeVm = ViewModelProvider(
            this,
            HomeVmFactory(weatherRepo, favoritesRepo)
        )[HomeViewModel::class.java]

        val searchVm = ViewModelProvider(
            this,
            SearchVmFactory(weatherRepo)
        )[SearchViewModel::class.java]

        val favoritesVm = ViewModelProvider(
            this,
            FavoritesVmFactory(favoritesRepo)
        )[FavoritesViewModel::class.java]

        /* ------------------- UI ------------------- */
        setContent {
            WheatherApplicationTheme {
                AppNav(
                    homeViewModel = homeVm,
                    searchViewModel = searchVm,
                    favoritesViewModel = favoritesVm
                )
            }
        }
    }
}

/* ------------------- FACTORIES ------------------- */

class HomeVmFactory(
    private val weatherRepo: WeatherRepo,
    private val favoritesRepo: FavouritesRepo
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(weatherRepo, favoritesRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}

class SearchVmFactory(
    private val weatherRepo: WeatherRepo
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchViewModel(weatherRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}

class FavoritesVmFactory(
    private val favoritesRepo: FavouritesRepo
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoritesViewModel(favoritesRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}