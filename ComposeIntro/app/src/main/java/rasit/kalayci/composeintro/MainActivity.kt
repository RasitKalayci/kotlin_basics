package rasit.kalayci.composeintro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import rasit.kalayci.composeintro.ui.theme.ComposeIntroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            mainScreen()
        }
    }
}

@Composable
fun mainScreen() {
    Column (modifier = Modifier.fillMaxSize().background(Color.DarkGray),
       verticalArrangement = Arrangement.Center,
        horizontalAlignment =  Alignment.CenterHorizontally

    ){
        CustomText("Fenerbahçee")
        Spacer(modifier = Modifier.height(20.dp))

        CustomText( "Asensiooo")
        Spacer(modifier = Modifier.height(20.dp))
        CustomText("Duran Emmi")
        Spacer(modifier = Modifier.height(20.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ){
            CustomText("BAŞKAN")
            Spacer(modifier = Modifier.width(20.dp))
            CustomText("Sadettin Saran")
        }

    }

}
@Composable
fun CustomText(text: String) {
    Text(
        text = text,
        color= Color.Blue,
        textAlign=androidx.compose.ui.text.style.TextAlign.Center,
        fontSize =30.sp,
        modifier = Modifier.background(color = Color.Yellow)
            .padding(5.dp,10.dp,5.dp,10.dp)
            //.fillMaxSize(0.5f)

    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
        mainScreen()

}