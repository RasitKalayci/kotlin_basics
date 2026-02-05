package rasit.kalayci.informationkeeping

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import rasit.kalayci.informationkeeping.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivityMainBinding
    var alinanKullaniciAdi : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        sharedPreferences = this.getSharedPreferences("rasit.kalayci.informationkeeping",Context.MODE_PRIVATE)
        alinanKullaniciAdi = sharedPreferences.getString("isim","")
        if (alinanKullaniciAdi== ""){
            binding.textView.text ="kullanıcı ismi"

        }
        else  {
            binding.textView.text = "kullanıcı ismi ${alinanKullaniciAdi}"
        }
    }
    fun kaydet (view: View){
        val kullaniciIsmi=binding.editTextText.text.toString()
        if(kullaniciIsmi == ""){
            Toast.makeText(this@MainActivity,"isminizi boş bırakmayın!" , Toast.LENGTH_LONG).show()
        }
        else{
            sharedPreferences.edit().putString("isim",kullaniciIsmi).apply()
            binding.textView.text= "kaydedilen isim : ${kullaniciIsmi}"
        }
    }
    fun sil (view: View){
        alinanKullaniciAdi = sharedPreferences.getString("isim","")
        if (alinanKullaniciAdi!= ""){
            sharedPreferences.edit().remove("isim").apply()

        }
        binding.textView.text = " kaydedilen isim "


    }
}


