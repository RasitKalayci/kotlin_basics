package rasit.kalayci.runnablekotlin

import android.os.Bundle
import android.os.Looper
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import rasit.kalayci.runnablekotlin.databinding.ActivityMainBinding
import java.util.logging.Handler

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var number = 0
    var runnable : Runnable= Runnable(){}
    var handler : Handler= Handler(Looper.getMainLooper())
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

    }
    fun start(view: View){
    number  = 0
        runnable = object :Runnable{
            override fun run() {
                number++
                binding.textView.text="Time:${number}"
                handler.postDelayed(runnable,1000)
            }

        }
handler.post(runnable)
        binding.button.isEnabled= false
    }
    fun stop(view: View){
        binding.button.isEnabled=true
        number = 0
        binding.textView.text = "Time: 0 "
        handler.removeCallbacks(runnable)


    }
}