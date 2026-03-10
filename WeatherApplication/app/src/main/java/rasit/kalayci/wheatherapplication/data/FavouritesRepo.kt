package rasit.kalayci.wheatherapplication.data

import androidx.compose.ui.text.font.Font
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import rasit.kalayci.wheatherapplication.data.room.FavouriteCity

import rasit.kalayci.wheatherapplication.data.room.FavouriteCityDao
import rasit.kalayci.wheatherapplication.models.City

class FavouritesRepo(private val dao: FavouriteCityDao) {


    fun observeFavourites(): Flow<List<City>> {
        return dao.observeAll().map{list ->
            list.map{entity ->
                City(
                    id = entity.id,
                    name = entity.name,
                    lat = entity.lat,
                    lon = entity.lon

                )
            }

        }
    }
    suspend fun add (city: City){
        val entity = FavouriteCity(
            id = city.id,
            name = city.name,
            lat = city.lat,
            lon = city.lon,
            createdAt = System.currentTimeMillis()
        )
        dao.upsert(entity)


    }
    suspend fun remove(cityId: String){
        dao.deleteById(cityId)
    }
    suspend fun observeIsFavourite(cityId: String): Flow<Boolean>{
        return dao.observeIsFavourite(cityId)
    }


}