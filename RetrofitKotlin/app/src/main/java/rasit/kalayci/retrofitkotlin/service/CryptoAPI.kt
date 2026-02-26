package rasit.kalayci.retrofitkotlin.service


import io.reactivex.rxjava3.core.Single
import rasit.kalayci.retrofitkotlin.model.CryptoModel
import retrofit2.http.GET

interface CryptoAPI {
    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    fun getData(): Single<List<CryptoModel>>

}