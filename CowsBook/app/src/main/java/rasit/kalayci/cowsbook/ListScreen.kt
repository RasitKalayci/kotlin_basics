package rasit.kalayci.cowsbook

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun CowsList(cowList: ArrayList<Cows>, navController: NavHostController){
    LazyColumn(contentPadding = PaddingValues(10.dp),
        modifier =  Modifier.fillMaxSize()
            .background(color = Color.White)
    ) {
        items(cowList.size) { index ->
            MainScren(cow = cowList[index], navController = navController, index = index)
        }
    }
}

@Composable
fun MainScren(cow: Cows, navController: NavHostController, index: Int){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp)
        .background(color = MaterialTheme.colorScheme.primaryContainer)
        .clickable {
            // Nesnenin kendisi yerine index'ini gönderiyoruz
            navController.navigate("detail_screen/$index")
        }
    )
    {
        Text (text = cow.name,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(10.dp),
        )

        Text(text = cow.country,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.padding(10.dp,0.dp,10.dp,10.dp)
        )
    }
}
