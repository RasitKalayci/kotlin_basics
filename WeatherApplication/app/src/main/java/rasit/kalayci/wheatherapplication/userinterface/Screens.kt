package rasit.kalayci.wheatherapplication.userinterface

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import rasit.kalayci.wheatherapplication.models.City
import rasit.kalayci.wheatherapplication.models.ForecastItem
import rasit.kalayci.wheatherapplication.models.Weather

/* ----------------------------- ROUTES ----------------------------- */

object Routes {
    const val HOME = "home"
    const val SEARCH = "search"
    const val FAVORITES = "favorites"
}

/* ----------------------------- APP NAV ROOT ----------------------------- */
/**
 * Buraya 3 ViewModel'i dışarıdan veriyoruz (shared).
 * - HomeViewModel tek instance: Search/Favorites şehir seçince bunu setCity ile güncelliyor.
 */
@Composable
fun AppNav(
    homeViewModel: HomeViewModel,
    searchViewModel: SearchViewModel,
    favoritesViewModel: FavoritesViewModel
) {
    val nav = rememberNavController()

    NavHost(
        navController = nav,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            HomeRoute(nav = nav, homeViewModel = homeViewModel)
        }
        composable(Routes.SEARCH) {
            SearchRoute(
                nav = nav,
                searchViewModel = searchViewModel,
                onCitySelected = { city ->
                    homeViewModel.setCity(city)
                    nav.popBackStack() // Home'a geri dön
                }
            )
        }
        composable(Routes.FAVORITES) {
            FavoritesRoute(
                nav = nav,
                favoritesViewModel = favoritesViewModel,
                onCitySelected = { city ->
                    homeViewModel.setCity(city)
                    nav.popBackStack() // Home'a geri dön
                }
            )
        }
    }
}

/* ----------------------------- HOME ----------------------------- */

@Composable
fun HomeRoute(
    nav: NavHostController,
    homeViewModel: HomeViewModel
) {
    val state by homeViewModel.state.collectAsState()

    // İlk açılış: default city set (tek sefer)
    LaunchedEffect(Unit) {
        if (state.city == null) {
            val defaultCity = City(
                id = "41.0082,28.9784",
                name = "Istanbul",
                lat = 41.0082,
                lon = 28.9784
            )
            homeViewModel.setCity(defaultCity)
        }
    }

    HomeScreen(
        state = state,
        onOpenSearch = { nav.navigate(Routes.SEARCH) },
        onOpenFavorites = { nav.navigate(Routes.FAVORITES) },
        onRefresh = { homeViewModel.refresh() },
        onToggleFavorite = { homeViewModel.toggleFavorite() },
        onDismissError = { homeViewModel.clearError() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeUiState,
    onOpenSearch: () -> Unit,
    onOpenFavorites: () -> Unit,
    onRefresh: () -> Unit,
    onToggleFavorite: () -> Unit,
    onDismissError: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(state.city?.name ?: "Weather") },
                actions = {
                    IconButton(onClick = onOpenSearch) { Text("🔍") }
                    IconButton(onClick = onOpenFavorites) { Text("⭐") }
                    IconButton(onClick = onRefresh) { Text("⟳") }
                    IconButton(onClick = onToggleFavorite, enabled = state.city != null) {
                        Text(if (state.isFavorite) "★" else "☆")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                state.errorMessage != null -> {
                    ErrorView(
                        message = state.errorMessage,
                        onRetry = onRefresh,
                        onDismiss = onDismissError,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                state.weather != null -> {
                    WeatherContent(
                        cityName = state.city?.name ?: "",
                        weather = state.weather,
                        forecast = state.forecast
                    )
                }
                else -> {
                    Text("Şehir seçili değil.", modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}

/* ----------------------------- SEARCH ----------------------------- */

@Composable
fun SearchRoute(
    nav: NavHostController,
    searchViewModel: SearchViewModel,
    onCitySelected: (City) -> Unit
) {
    val state by searchViewModel.state.collectAsState()

    SearchScreen(
        state = state,
        onBack = { nav.popBackStack() },
        onQueryChange = { searchViewModel.onQueryChange(it) },
        onSearch = { searchViewModel.search() },
        onCityClick = { city -> onCitySelected(city) },
        onDismissError = { searchViewModel.clearError() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    state: SearchUiState,
    onBack: () -> Unit,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    onCityClick: (City) -> Unit,
    onDismissError: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Şehir Ara") },
                navigationIcon = { IconButton(onClick = onBack) { Text("←") } }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = state.query,
                onValueChange = onQueryChange,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Şehir") },
                singleLine = true
            )

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = onSearch) { Text("Ara") }
                if (state.isLoading) CircularProgressIndicator(modifier = Modifier.size(24.dp))
            }

            if (state.errorMessage != null) {
                ErrorInline(message = state.errorMessage, onDismiss = onDismissError)
            }

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(state.results) { city ->
                    CityRow(city = city, onClick = { onCityClick(city) })
                }
            }
        }
    }
}

/* ----------------------------- FAVORITES ----------------------------- */

@Composable
fun FavoritesRoute(
    nav: NavHostController,
    favoritesViewModel: FavoritesViewModel,
    onCitySelected: (City) -> Unit
) {
    val state by favoritesViewModel.state.collectAsState()

    FavoritesScreen(
        state = state,
        onBack = { nav.popBackStack() },
        onCityClick = { city -> onCitySelected(city) },
        onRemove = { cityId -> favoritesViewModel.remove(cityId) },
        onDismissError = { favoritesViewModel.clearError() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    state: FavoritesUiState,
    onBack: () -> Unit,
    onCityClick: (City) -> Unit,
    onRemove: (String) -> Unit,
    onDismissError: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Favoriler") },
                navigationIcon = { IconButton(onClick = onBack) { Text("←") } }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (state.isLoading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }

            if (state.errorMessage != null) {
                ErrorInline(message = state.errorMessage, onDismiss = onDismissError)
            }

            if (!state.isLoading && state.favorites.isEmpty()) {
                Text("Henüz favori yok. Home ekranında ☆ ile ekleyebilirsin.")
                return@Column
            }

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(state.favorites) { city ->
                    FavoriteRow(
                        city = city,
                        onClick = { onCityClick(city) },
                        onRemove = { onRemove(city.id) }
                    )
                }
            }
        }
    }
}

/* ----------------------------- UI PIECES ----------------------------- */

@Composable
private fun WeatherContent(
    cityName: String,
    weather: Weather,
    forecast: List<ForecastItem>
) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item { WeatherCard(cityName = cityName, weather = weather) }
        item { Text("Forecast", style = MaterialTheme.typography.titleMedium) }
        items(forecast) { ForecastRow(it) }
    }
}

@Composable
private fun WeatherCard(cityName: String, weather: Weather) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(cityName, style = MaterialTheme.typography.titleLarge)
            Text("${weather.temp}°C  (Feels: ${weather.feelsLike}°C)")
            Text("Wind: ${weather.windSpeed} m/s")
            if (weather.icon.isNotBlank()) Text("Icon: ${weather.icon}")
        }
    }
}

@Composable
private fun ForecastRow(item: ForecastItem) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("t=${item.time}")
            Text("${item.temp}°C")
            Text(item.icon)
        }
    }
}

@Composable
private fun CityRow(city: City, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(city.name, style = MaterialTheme.typography.titleMedium)
            Text("lat=${city.lat}, lon=${city.lon}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun FavoriteRow(city: City, onClick: () -> Unit, onRemove: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .clickable { onClick() }
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(city.name, style = MaterialTheme.typography.titleMedium)
                Text("lat=${city.lat}, lon=${city.lon}", style = MaterialTheme.typography.bodySmall)
            }
            TextButton(onClick = onRemove) { Text("Sil") }
        }
    }
}

@Composable
private fun ErrorView(
    message: String,
    onRetry: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("Hata: $message")
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Button(onClick = onRetry) { Text("Tekrar dene") }
                OutlinedButton(onClick = onDismiss) { Text("Kapat") }
            }
        }
    }
}

@Composable
private fun ErrorInline(message: String, onDismiss: () -> Unit) {
    Card {
        Row(
            modifier = Modifier.padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Hata: $message", modifier = Modifier.weight(1f))
            TextButton(onClick = onDismiss) { Text("Kapat") }
        }
    }
}
