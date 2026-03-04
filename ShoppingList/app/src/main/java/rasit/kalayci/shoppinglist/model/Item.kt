package rasit.kalayci.shoppinglist.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Item (
    @ColumnInfo(name = "name") val itemname: String,
    @ColumnInfo("storename") val storename: String?,
    @ColumnInfo("price") val price: String?,
    @ColumnInfo("image") var iamge: ByteArray?
    ){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}