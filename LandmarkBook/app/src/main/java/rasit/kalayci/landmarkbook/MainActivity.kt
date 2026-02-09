package rasit.kalayci.landmarkbook

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import rasit.kalayci.landmarkbook.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var landmarkList : ArrayList<Landmark>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        enableEdgeToEdge()
        landmarkList= ArrayList<Landmark>()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val redlight = Landmark("Red Light","Netherlands",R.drawable.redlight)
        val beyazsaray = Landmark("White Palace","United States",R.drawable.beyazsaray)
        val efes = Landmark("Efes","Türkiye",R.drawable.efes)
        val towerBridge = Landmark("Tower Bridge","United Kingdom ",R.drawable.towerbridge)


        landmarkList.add(redlight)
        landmarkList.add(beyazsaray)
        landmarkList.add(efes)
        landmarkList.add(towerBridge)
        binding.recyclerView.layoutManager= LinearLayoutManager(this@MainActivity)
        val landmarkAdapter = LandmarkAdapter(landmarkList)
        binding.recyclerView.adapter = landmarkAdapter


        /*
         val adapter = ArrayAdapter(this@MainActivity,android.R.layout.simple_list_item_activated_1,landmarkList.map{landmark ->landmark.name})
        binding.listView.adapter=adapter
        binding.listView.onItemClickListener= AdapterView.OnItemClickListener{parent,view, i, lng ->
            val intent = Intent(this@MainActivity, DetailsActivity::class.java)
            intent.putExtra("landmark",landmarkList.get(i))
            startActivity(intent)
        }*/
    }
}