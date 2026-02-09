package rasit.kalayci.catchthekenny

import android.content.DialogInterface
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import rasit.kalayci.catchthekenny.databinding.ActivityMainBinding
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var score = 0
    var imageArray = ArrayList<ImageView>()
    var runnable = Runnable{}
    var handler = Handler(Looper.getMainLooper())

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
        imageArray.add(binding.imageView)
        imageArray.add(binding.imageView1)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        hideImages()

        object: CountDownTimer(10000,1000){
            override fun onFinish() {
                binding.timeText.text="Time:0"
                handler.removeCallbacks(runnable)
                for(image in imageArray){
                 image.visibility= View.INVISIBLE
                }
                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("GAME OVER")
                alert.setMessage("tekrar oynamak ister misin?")
                alert.setNegativeButton("Hayır",DialogInterface.OnClickListener{dialogInterface,i->

                    Toast.makeText(this@MainActivity,"game over", Toast.LENGTH_LONG).show() } )



                alert.setPositiveButton("Evet", DialogInterface.OnClickListener{dialogInterface,i->
                val intentFromMain= intent
                    finish()
                    startActivity(intentFromMain)
                })
                alert.show()
            }

            override fun onTick(millisUntilFinished: Long) {
                binding.timeText.text = "Time: ${millisUntilFinished/1000}"
            }

        }.start()


    }
    fun hideImages(){
        runnable = object : Runnable{
            override fun run() {
                for (image in imageArray){

                    image.visibility=View.INVISIBLE
                }
                val random = Random
                val randomIndex=random.nextInt(9)
                imageArray[randomIndex].visibility=View.VISIBLE
                handler.postDelayed(runnable,500)
            }

        }
        handler.post(runnable)

    }
    fun increaseScore (view: View){
        score = score+1
        binding.scoreText.text ="Score: ${score}"

    }
}