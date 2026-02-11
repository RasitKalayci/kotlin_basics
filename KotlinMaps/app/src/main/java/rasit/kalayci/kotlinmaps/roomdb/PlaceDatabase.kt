package rasit.kalayci.kotlinmaps.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import rasit.kalayci.kotlinmaps.model.Place

@Database(entities = [Place::class], version = 1)
abstract class PlaceDatabase : RoomDatabase() {
    abstract fun placeDao(): PlaceDao

}