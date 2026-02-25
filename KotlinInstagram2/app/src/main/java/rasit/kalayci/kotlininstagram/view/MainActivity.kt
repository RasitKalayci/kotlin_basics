package rasit.kalayci.kotlininstagram.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import rasit.kalayci.kotlininstagram.R
import rasit.kalayci.kotlininstagram.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        enableEdgeToEdge()
        // Initialize Firebase Auth
        auth = Firebase.auth
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this, FeedActivity::class.java)
            startActivity(intent)
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
    fun signinClick(view: View){
        val email = binding.emailText.text.toString()
        val password = binding.passwordText.text.toString()
        if (email.isNotEmpty() && password.isNotEmpty()){
            auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
                //success
                val intent = Intent(this@MainActivity, FeedActivity::class.java)
                startActivity(intent)
                finish()

            }.addOnFailureListener {
                Toast.makeText(this@MainActivity,it.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }
    fun signupClick(view: View){
        val email = binding.emailText.text.toString()
        val password = binding.passwordText.text.toString()
        if (email.isNotEmpty() && password.isNotEmpty()){
            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {// bu listener sonuç beklediğimizde çalışır
                //success
                val intent = Intent(this@MainActivity, FeedActivity::class.java)
                startActivity(intent)
                finish()


            }.addOnFailureListener {
                Toast.makeText(this@MainActivity,it.localizedMessage, Toast.LENGTH_LONG).show()
            }


        }

    }

}