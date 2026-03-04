package rasit.kalayci.shoppinglist.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import rasit.kalayci.shoppinglist.model.Item

@Composable
fun ListScreen(itemList: List<Item>, onItemClick: (Int) -> Unit, onAddClick: () -> Unit) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { onAddClick() }) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            items(itemList) { item ->
                ItemRow(item = item, onClick = { onItemClick(item.id) })
            }
        }
    }
}

@Composable
fun ItemRow(item: Item, onClick: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .background(color = MaterialTheme.colorScheme.surfaceVariant, shape = MaterialTheme.shapes.medium)
        .clickable { onClick() }
        .padding(16.dp)
    ) {
        Text(text = item.itemname, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Text(text = "Fiyat: ${item.price ?: "-"} TL", style = MaterialTheme.typography.bodyLarge)
    }
}
