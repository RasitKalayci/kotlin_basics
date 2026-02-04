package rasit.kalayci.workers

class Calisanlar (val isim : String,var yas : Int,var departman : String ,maas : Int){

    private val _maas = maas

    fun maasGoster(){
        println(this._maas)
    }
}
