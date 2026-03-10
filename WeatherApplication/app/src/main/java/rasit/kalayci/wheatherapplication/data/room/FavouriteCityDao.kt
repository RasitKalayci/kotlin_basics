package rasit.kalayci.wheatherapplication.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteCityDao {
    @Query("SELECT * FROM favourite_cities ORDER BY createdAt DESC")
    fun observeAll(): Flow<List<FavouriteCity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert (city: FavouriteCity)

    @Query("DELETE FROM favourite_cities WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query( "SELECT EXISTS (SELECT 1 FROM favourite_cities WHERE id = :cityId)")
    fun observeIsFavourite(cityId: String): Flow<Boolean>
}
