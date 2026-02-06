package rasit.kalayci.alertdiaologkotlin

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import rasit.kalayci.alertdiaologkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

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
        Toast.makeText(this@MainActivity,"Welcome!",Toast.LENGTH_LONG).show()
    }
    fun save (view: View){
val alert = AlertDialog.Builder(this@MainActivity)
        alert.setTitle("Save")
        alert.setMessage("emin misin?")
        alert.setPositiveButton("yes",object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                Toast.makeText(this@MainActivity,"kaydedildi!", Toast.LENGTH_LONG).show()
            }

        })
        alert.setNegativeButton("No",object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                Toast.makeText(this@MainActivity,"kayıt iptal edildi", Toast.LENGTH_LONG).show()
            }
        })
        alert.show()
    }
}