package rasit.kalayci.cowsbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import rasit.kalayci.cowsbook.ui.theme.CowsBookTheme

class MainActivity : ComponentActivity() {
    private val cowList = ArrayList<Cows>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        if (cowList.isEmpty()) {
            getData()
        }
        
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            CowsBookTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavHost(navController = navController, startDestination = "list_screen") {
                            composable("list_screen") {
                                CowsList(cowList = cowList, navController = navController)
                            }
                            
                            // Detay ekranı için rota ve parametre tanımı
                            composable(
                                route = "detail_screen/{cowIndex}",
                                arguments = listOf(navArgument("cowIndex") { type = NavType.IntType })
                            ) { backStackEntry ->
                                // Gönderilen index'i alıyoruz
                                val index = backStackEntry.arguments?.getInt("cowIndex")
                                if (index != null && index < cowList.size) {
                                    DetailScreen(cow = cowList[index])
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun getData() {
        val angus = Cows("Angus", "England", R.drawable.angus)
        val holstein = Cows("Holstein", "Germany", R.drawable.holstein)
        val jersey = Cows("Jersey", "England", R.drawable.jersey)
        val sarole = Cows("Sarole", "France", R.drawable.sarole)
        val simental = Cows("Simental", "France", R.drawable.simental)
        
        cowList.add(angus)
        cowList.add(holstein)
        cowList.add(jersey)
        cowList.add(sarole)
        cowList.add(simental)
        cowList.add(angus)
        cowList.add(holstein)
        cowList.add(jersey)
        cowList.add(sarole)
        cowList.add(simental)
        cowList.add(angus)
        cowList.add(holstein)
        cowList.add(jersey)
        cowList.add(sarole)
        cowList.add(simental)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CowsBookTheme {
        DetailScreen(cow = Cows("Angus", "England", R.drawable.angus))
    }
}
