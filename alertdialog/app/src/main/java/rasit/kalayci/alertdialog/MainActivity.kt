package rasit.kalayci.alertdialog

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import rasit.kalayci.alertdialog.databinding.ActivityMainBinding

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
        Toast.makeText(this,"hoş geldiniz", LENGTH_LONG).show()
       /* binding.button.setOnClickListener(object :View.OnClickListener {
            override fun onClick(v: View?) {
                println("butona tıklandı")

            }

        })*/

    }
    fun kaydet(view: View){
        val alert = AlertDialog.Builder(this@MainActivity)
        alert.setTitle("Kayıt et")
        alert.setMessage("kayıt etmek ister misiniz ?")
        alert.setPositiveButton("evet"){dialog, which ->
            Toast.makeText(this@MainActivity,"kayıt edildi", Toast.LENGTH_LONG).show()

        }
alert.setNegativeButton("hayır"){dialog, which ->
    Toast.makeText(this@MainActivity,"kayıt iptal edildi", Toast.LENGTH_LONG).show()
}
        /*alert.setNegativeButton("hayır",object : DialogInterface.OnClickListener){
     override fun onClick (dialog: DialogInterface?, which: Int){
         Toast.makeText(this@MainActivity,"kayıt iptaş edildi", Toast.LENGTH_LONG).show()
     }
 }*/

        alert.show()


    }
}