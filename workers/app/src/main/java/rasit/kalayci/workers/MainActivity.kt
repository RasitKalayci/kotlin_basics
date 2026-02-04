package rasit.kalayci.workers

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var calışan1 = Calisanlar("Rasit",31,"yazılım",244)
        var calışan2 = Calisanlar("Ahmet",28,"satın alma",654)
        var calışan3 = Calisanlar("Mehmet",26,"İK",45)
        var calışan4 = Calisanlar("İlayda",30,"Yönetim",298)
        var calışan5 = Calisanlar("Merve",45,"pazarlama",2447)
        var calışan6 = Calisanlar("Ömer",32,"üretim",2448)
        var calışan7 = Calisanlar("Nida",41,"ustabaşı",2448)
        var calışan8 = Calisanlar("Eren",24,"sistem destek",2444)
        var calışan9 = Calisanlar("Ayşe",62,"çaycı",2474)
        var calışan10 = Calisanlar("Ali",39,"dış ticaret",2144)

        val calısanlar =arrayListOf<Calisanlar>(calışan1,calışan2,calışan3,calışan4,calışan5,calışan6,calışan7,calışan8,calışan9,calışan10)

 val yirmiBestenküçük = calısanlar.filter{it.yas<25}.map{it.isim}
println(yirmiBestenküçük)
val yasıbüyük = calısanlar.filter{it.yas > 30 }.filter{it.departman== "yazılım"} .map{it.maasGoster()}

    }

}