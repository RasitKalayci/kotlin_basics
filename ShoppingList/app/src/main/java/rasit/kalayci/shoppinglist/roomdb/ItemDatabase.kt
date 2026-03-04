package rasit.kalayci.shoppinglist.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import rasit.kalayci.shoppinglist.model.Item

@Database(entities = [Item::class], version = 1)
abstract class ItemDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao

}