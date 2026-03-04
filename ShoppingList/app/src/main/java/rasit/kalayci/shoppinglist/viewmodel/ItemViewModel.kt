package rasit.kalayci.shoppinglist.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import rasit.kalayci.shoppinglist.model.Item
import rasit.kalayci.shoppinglist.roomdb.ItemDatabase

class ItemViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Room.databaseBuilder(
        application.applicationContext,
        ItemDatabase::class.java, "Items"
    ).build()
    private val itemDao = db.itemDao()

    val itemlist = mutableStateOf<List<Item>>(listOf())
    val selectedItem = mutableStateOf<Item?>(null)

    fun getItemList() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = itemDao.getAllItems()
            withContext(Dispatchers.Main) {
                itemlist.value = list
            }
        }
    }

    fun getItem(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val item = itemDao.getItemById(id)
            withContext(Dispatchers.Main) {
                selectedItem.value = item
            }
        }
    }

    fun saveItem(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            itemDao.insertItem(item)
            getItemList() // Kaydettikten sonra listeyi güncelle
        }
    }

    fun deleteItem(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            itemDao.deleteItem(item)
            getItemList() // Sildikten sonra listeyi güncelle
        }
    }
}
