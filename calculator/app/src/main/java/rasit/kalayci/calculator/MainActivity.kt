package rasit.kalayci.calculator

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import rasit.kalayci.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var numara1: Double? = null
    var numara2 : Double?= null
    var sonuc : Double?= null

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
        val text1 = binding.editTextNumber.text.toString()
        val text2 = binding.editTextNumber2.text.toString()
    }
    fun toplama(view: View){
        numara1 = binding.editTextNumber.text.toString().toDoubleOrNull()
        numara2 = binding.editTextNumber2.text.toString().toDoubleOrNull()
        if(numara1 != null && numara2 != null){
            sonuc = numara1!!+ numara2!!
        binding.textView.text= "sonuç ${sonuc}" }
        else{
            binding.textView.text = "bir sayı giriniz"
        }
    }
    fun cikarma(view: View){
        numara1 = binding.editTextNumber.text.toString().toDoubleOrNull()
        numara2 = binding.editTextNumber2.text.toString().toDoubleOrNull()
        if(numara1 != null && numara2 != null){
            sonuc = numara1!!- numara2!!
            binding.textView.text= "sonuç ${sonuc}" }
        else{
            binding.textView.text = "bir sayı giriniz"
        }
    }
    fun bolme(view: View){
        numara1 = binding.editTextNumber.text.toString().toDoubleOrNull()
        numara2 = binding.editTextNumber2.text.toString().toDoubleOrNull()
        if(numara1 != null && numara2 != null){
            if(numara2== 0.0){
                binding.textView.text="sayı sıfıra  bölünemez"
            }
            else{
            sonuc = numara1!!/ numara2!!
            binding.textView.text= "sonuç ${sonuc}" }}
        else{
            binding.textView.text = "bir sayı giriniz"
        }
    }
    fun carpma(view: View){
        numara1 = binding.editTextNumber.text.toString().toDoubleOrNull()
        numara2 = binding.editTextNumber2.text.toString().toDoubleOrNull()
        if(numara1 != null && numara2 != null){
            sonuc = numara1!!* numara2!!
            binding.textView.text= "sonuç ${sonuc}" }
        else{
            binding.textView.text = "bir sayı giriniz"
        }
    }
}