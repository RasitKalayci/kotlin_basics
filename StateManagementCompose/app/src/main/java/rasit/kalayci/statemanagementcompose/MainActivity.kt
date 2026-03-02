package rasit.kalayci.statemanagementcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import rasit.kalayci.statemanagementcompose.ui.theme.StateManagementComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           MainScreen()
        }
    }
}
@Composable
fun MainScreen() {
    var myString= remember{ mutableStateOf("")}
    Surface(modifier = Modifier.fillMaxSize(),
        color = Color.LightGray) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

        specialText("hello raşit")
        Spacer(modifier = Modifier.padding(10.dp))
        specialText("hoş geldin ")
            Spacer(modifier = Modifier.padding(10.dp))
        SpecialTextFiels(myString.value){
            myString.value=it

        }

    }}



}
@Composable
fun specialText(string: String) {
    Text(
        text = string,
        fontSize = 26.sp,
        fontStyle= FontStyle.Italic,
        fontFamily = FontFamily.SansSerif)
}
@Composable
fun SpecialTextFiels(string: String, function : (String) -> Unit) {

     TextField(value = string, onValueChange = function)

}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StateManagementComposeTheme {
      MainScreen()
    }
}