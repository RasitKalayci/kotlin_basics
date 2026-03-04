package rasit.kalayci.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import rasit.kalayci.shoppinglist.screens.AddItemScreen
import rasit.kalayci.shoppinglist.screens.DetailsScreen
import rasit.kalayci.shoppinglist.screens.ListScreen
import rasit.kalayci.shoppinglist.ui.theme.ShoppingListTheme
import rasit.kalayci.shoppinglist.viewmodel.ItemViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingListTheme {
                val navController = rememberNavController()
                val viewModel: ItemViewModel = viewModel()

                NavHost(navController = navController, startDestination = "list_screen") {
                    // Liste Ekranı
                    composable("list_screen") {
                        LaunchedEffect(Unit) {
                            viewModel.getItemList()
                        }
                        ListScreen(
                            itemList = viewModel.itemlist.value,
                            onItemClick = { id ->
                                navController.navigate("details_screen/$id")
                            },
                            onAddClick = {
                                navController.navigate("add_item_screen")
                            }
                        )
                    }
                    // Detay Ekranı
                    composable(
                        "details_screen/{itemId}",
                        arguments = listOf(navArgument("itemId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val itemId = backStackEntry.arguments?.getInt("itemId") ?: 0
                        DetailsScreen(itemId, viewModel, navController)
                    }
                    // Ürün Ekleme Ekranı
                    composable("add_item_screen") {
                        AddItemScreen(viewModel, navController)
                    }
                }
            }
        }
    }
}
