package rasit.kalayci.storingdata

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import rasit.kalayci.storingdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPref : SharedPreferences
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
        sharedPref =this.getSharedPreferences("rasit.kalayci.storingdata",MODE_PRIVATE)
        val userage = sharedPref.getInt("age",-1)
        if (userage== -1){
            binding.textView.text= "your age:"
        }
        else{
            binding.textView.text = "your age : ${userage}"
        }
    }
    fun kaydet(view: View){
        val myAge = binding.editTextText.text.toString().toIntOrNull()
        if (myAge!= null ){
            binding.textView.text ="may age : ${myAge}"
            sharedPref.edit().putInt("age",myAge).apply()


        }

    }
    fun sil (view: View){
var userage =sharedPref.getInt("age",-1)
        if(userage!=-1){
            sharedPref.edit().remove("age").apply()
            binding.textView.text = "your age: "
        }

    }
}