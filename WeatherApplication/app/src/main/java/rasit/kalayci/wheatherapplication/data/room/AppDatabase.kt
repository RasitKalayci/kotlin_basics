package rasit.kalayci.wheatherapplication.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FavouriteCity::class],
    version = 1,
    exportSchema = false

)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favouriteCityDao(): FavouriteCityDao
}