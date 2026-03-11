package rasit.kalayci.easymeet

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class ConferenceActivity : AppCompatActivity() {
    lateinit var meetingId: String
    lateinit var username: String
    lateinit var meetingIdTextView: TextView
    lateinit var shareButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_conference)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        meetingIdTextView = findViewById(R.id.meeting_id_textview)
        shareButton = findViewById(R.id.share_button)
        meetingId = intent.getStringExtra("meetingId").toString()
        username = intent.getStringExtra("username").toString()


        meetingIdTextView.setText("Meeting id : $meetingId")
        addFragment()
        shareButton.setOnClickListener {
            var sharemessage = "Join Meeting : $meetingId"
            var shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, sharemessage)
                type = "text/plain"
            }
            startActivity(Intent.createChooser(shareIntent, "Share to"))

        }



    }
    fun addFragment() {
        val appID: Long = AppConstants.appId
        val appSign: String? = AppConstants.appSign

        var conferenceID: String? = meetingId
        var userID: String? = username
        var userName: String? = username


        val config: ZegoUIKitPrebuiltVideoConferenceConfig =
            ZegoUIKitPrebuiltVideoConferenceConfig()
        val fragment: ZegoUIKitPrebuiltVideoConferenceFragment =
            ZegoUIKitPrebuiltVideoConferenceFragment.newInstance(
                appID,
                appSign,
                userID,
                userName,
                conferenceID,
                config
            )

        getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commitNow()
    }
}