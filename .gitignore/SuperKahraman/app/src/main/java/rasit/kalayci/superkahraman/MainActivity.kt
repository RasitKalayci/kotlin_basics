package rasit.kalayci.superkahraman

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.SupervisorJob
import rasit.kalayci.superkahraman.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var superKahramanListesi : ArrayList<Superkahraman>
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
        val superman = Superkahraman("superman","gazeteci",R.drawable.superman)
        val dostorstarange = Superkahraman("doctorStrange","doktor",R.drawable.doctorstrange)
        val batman = Superkahraman("batman","patron",R.drawable.widow)
        val scarlet = Superkahraman("scarlet", "cadı",R.drawable.scarletw)
        superKahramanListesi= arrayListOf(superman,dostorstarange,batman,scarlet)
        val adapter = SuperKahramanAdapter(superKahramanListesi)
        binding.superkahramanrecycleview.layoutManager = LinearLayoutManager(this)
       binding.superkahramanrecycleview.adapter = adapter

    }
}