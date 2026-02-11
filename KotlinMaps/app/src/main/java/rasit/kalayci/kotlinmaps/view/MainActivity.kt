package rasit.kalayci.kotlinmaps.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import rasit.kalayci.kotlinmaps.view.MapsActivity
import rasit.kalayci.kotlinmaps.R
import rasit.kalayci.kotlinmaps.adapter.PlaceAdapter
import rasit.kalayci.kotlinmaps.databinding.ActivityMainBinding
import rasit.kalayci.kotlinmaps.model.Place
import rasit.kalayci.kotlinmaps.roomdb.PlaceDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
val db = Room.databaseBuilder(applicationContext, PlaceDatabase::class.java,"Places").build()
        val placeDao = db.placeDao()
compositeDisposable.add(
    placeDao.getAll()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread()).subscribe(this::handleResponse)
)

        setSupportActionBar(binding.toolbar)

        // Toolbar'ın durum çubuğunun altında kalmasını önlemek için inset ayarı
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
    private fun handleResponse(placeList: List<Place>) {
        binding.recyclerView.layoutManager= LinearLayoutManager(this)
        val adapter = PlaceAdapter(placeList)
        binding.recyclerView.adapter = adapter

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.place_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_place) {
            val intent = Intent(this, MapsActivity::class.java)
            intent.putExtra("info", "new")
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}