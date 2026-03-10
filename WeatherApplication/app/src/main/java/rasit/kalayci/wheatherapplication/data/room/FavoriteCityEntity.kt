package rasit.kalayci.wheatherapplication.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName  = "favourite_cities")
data class FavouriteCity(
    @PrimaryKey
    val id: String,
    val name: String,
    val lat: Double,
    val lon: Double,
    val createdAt : Long
)