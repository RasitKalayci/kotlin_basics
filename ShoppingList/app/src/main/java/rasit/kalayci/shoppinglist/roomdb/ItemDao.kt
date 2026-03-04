package rasit.kalayci.shoppinglist.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import rasit.kalayci.shoppinglist.model.Item

@Dao
interface ItemDao {
    @Query("SELECT * FROM Item")
    suspend fun getAllItems(): List<Item>

    @Query("SELECT * FROM Item WHERE id = :id")
    suspend fun getItemById(id: Int): Item?

    @Insert
    suspend fun insertItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)
}
