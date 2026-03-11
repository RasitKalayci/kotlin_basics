package rasit.kalayci.easymeet

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun LoginPage(modifier: Modifier = Modifier) {
    var meetingId by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var context = LocalContext.current

    Column(
        modifier = modifier.fillMaxSize()
            .background(colorResource(id=R.color.my_background_color))
    ){
        Box(modifier = Modifier
            .fillMaxSize()
            .weight(1f)
        ){
            Text(text = "Easy Meet", color = Color.White,
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                modifier= Modifier.align(Alignment.Center))

        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(32.dp,32.dp))
                .background(Color.White)
                .padding(32.dp)
        ){
            Column() {
                OutlinedTextField(
                    value = meetingId,
                    onValueChange = {if(it.length<=10) meetingId = it},


                    label = {
                        Text(text = "Meeting Id") },
                            modifier=Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)


                )
                OutlinedTextField(
                    value = username,
                    onValueChange = {username= it},


                    label = {
                        Text(text = "Your Name") },
                    modifier=Modifier.fillMaxWidth(),

                    )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    enabled = meetingId.length == 10  && username.isNotEmpty(),
                    onClick = {
                        val intent = Intent(context, ConferenceActivity::class.java)
                        intent.putExtra("meetingId", meetingId)
                        intent.putExtra("username", username)
                        context.startActivity(intent)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id=R.color.my_background_color)

                    )
                )

                {
                    Text(text = "Join Meeting", color = Color.White)
                }
                Button(
                    enabled =   username.isNotEmpty(),
                    onClick = {
                        var randommeetingId = (1..10).map { ('0'..'9').random() }.joinToString("")
                        val intent = Intent(context, ConferenceActivity::class.java)
                        intent.putExtra("meetingId", randommeetingId)
                        intent.putExtra("username", username)
                        context.startActivity(intent)

                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id=R.color.my_background_color)

                    )
                )

                {
                    Text(text = "Create Meeting", color = Color.White)
                }





            }
        }

    }

}