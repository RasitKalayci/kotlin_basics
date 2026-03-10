package rasit.kalayci.wheatherapplication.userinterface

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import rasit.kalayci.wheatherapplication.data.FavouritesRepo
import rasit.kalayci.wheatherapplication.data.WeatherRepo
import rasit.kalayci.wheatherapplication.models.City
import rasit.kalayci.wheatherapplication.models.ForecastItem
import rasit.kalayci.wheatherapplication.models.Weather


/* ----------------------------- HOME ----------------------------- */

data class HomeUiState(
    val city: City? = null,
    val weather: Weather? = null,
    val forecast: List<ForecastItem> = emptyList(),
    val isFavorite: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class HomeViewModel(
    private val weatherRepo: WeatherRepo,
    private val favoritesRepo: FavouritesRepo
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state: StateFlow<HomeUiState> = _state.asStateFlow()

    private var favoriteJob: kotlinx.coroutines.Job? = null

    fun setCity(city: City) {
        _state.update { it.copy(city = city, errorMessage = null) }
        observeFavorite(city.id)
        refresh()
    }

    fun refresh() {
        val city = _state.value.city ?: return

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                val weather = weatherRepo.getCurrentWeather(city)
                val forecast = weatherRepo.getForecast(city, maxItems = 12)
                _state.update {
                    it.copy(
                        weather = weather,
                        forecast = forecast,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "Bir hata oluştu"
                    )
                }
            }
        }
    }

    fun toggleFavorite() {
        val city = _state.value.city ?: return
        viewModelScope.launch {
            try {
                if (_state.value.isFavorite) {
                    favoritesRepo.remove(city.id)
                } else {
                    favoritesRepo.add(city)
                }
            } catch (e: Exception) {
                _state.update { it.copy(errorMessage = e.message ?: "Favori işlemi başarısız") }
            }
        }
    }

    fun clearError() {
        _state.update { it.copy(errorMessage = null) }
    }

    private fun observeFavorite(cityId: String) {
        favoriteJob?.cancel()
        favoriteJob = viewModelScope.launch {
            favoritesRepo.observeIsFavourite(cityId)
                .distinctUntilChanged()
                .collect { fav ->
                    _state.update { it.copy(isFavorite = fav) }
                }
        }
    }
}

/* ----------------------------- SEARCH ----------------------------- */

data class SearchUiState(
    val query: String = "",
    val results: List<City> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class SearchViewModel(
    private val weatherRepo: WeatherRepo
) : ViewModel() {

    private val _state = MutableStateFlow(SearchUiState())
    val state: StateFlow<SearchUiState> = _state.asStateFlow()

    fun onQueryChange(text: String) {
        _state.update { it.copy(query = text) }
    }

    fun search() {
        val q = _state.value.query.trim()
        if (q.isBlank()) {
            _state.update { it.copy(results = emptyList(), errorMessage = "Şehir adı yaz") }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                val cities = weatherRepo.searchCities(q, limit = 10)
                _state.update { it.copy(results = cities, isLoading = false) }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, errorMessage = e.message ?: "Arama hatası") }
            }
        }
    }

    fun clearError() {
        _state.update { it.copy(errorMessage = null) }
    }
}

/* ----------------------------- FAVORITES ----------------------------- */

data class FavoritesUiState(
    val favorites: List<City> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class FavoritesViewModel(
    private val favoritesRepo: FavouritesRepo
) : ViewModel() {

    private val _state = MutableStateFlow(FavoritesUiState(isLoading = true))
    val state: StateFlow<FavoritesUiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            favoritesRepo.observeFavourites()
                .catch { e ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = e.message ?: "Favoriler yüklenemedi"
                        )
                    }
                }
                .collect { list ->
                    _state.update { it.copy(favorites = list, isLoading = false) }
                }
        }
    }

    fun remove(cityId: String) {
        viewModelScope.launch {
            try {
                favoritesRepo.remove(cityId)
            } catch (e: Exception) {
                _state.update { it.copy(errorMessage = e.message ?: "Silme başarısız") }
            }
        }
    }

    fun clearError() {
        _state.update { it.copy(errorMessage = null) }
    }
}
