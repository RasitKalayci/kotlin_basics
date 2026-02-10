package rasit.kalayci.kotlinsqllite

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        try{
            val myDatabase = this.openOrCreateDatabase("Musicians",MODE_PRIVATE,null)
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS musicians (id INTEGER PRIMARY KEY,name VARCHAR,age INT)")
            //myDatabase.execSQL("INSERT INTO musicians (name,age ) VALUES ('James',50)")
            val cursor = myDatabase.rawQuery("SELECT * FROM musicians WHERE name LIKE '%s'",null)

            val nameIx = cursor.getColumnIndex("name")
            val ageIx = cursor.getColumnIndex("age")
            val idIx = cursor.getColumnIndex("id")


            while (cursor.moveToNext()){
                println("name: "+ cursor.getString(nameIx))
                println ("age: "+ cursor.getInt(ageIx))
                println("id: "+ cursor.getInt(idIx))
            }
            cursor.close()

        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}