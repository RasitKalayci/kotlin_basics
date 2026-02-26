package rasit.kalayci.retrofitkotlin.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CryptoModel(
    @SerialName("currency")
    val currency: String,
    @SerialName("price")
    val price: String
)