package rasit.kalayci.statemanagementcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable

fun OtherScreen () {
    Column(Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        var myString= remember{ mutableStateOf("hello Android")}

        TextField(value = myString.value, onValueChange = {
            myString.value=it


        })
        Spacer(modifier = Modifier.padding(10.dp))


        var textString= remember { mutableStateOf("hello")}

        Text(
            text = textString.value,
            fontSize = 26.sp
        )
        Spacer(modifier = Modifier.padding(10.dp))


        Button(onClick ={
            textString.value="Android"

        } ) {
            Text(text = "Button")

        }
        Spacer(modifier = Modifier.padding(10.dp))

        // painterResource kullanarak görseli ekledik
        Image(
            painter = painterResource(id = R.drawable.karainek),
            contentDescription = "Kara İnek",
            modifier = Modifier.padding(10.dp)
        )

    }
}