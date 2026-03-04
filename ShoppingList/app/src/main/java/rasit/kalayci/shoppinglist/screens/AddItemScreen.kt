package rasit.kalayci.shoppinglist.screens

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import rasit.kalayci.shoppinglist.model.Item
import rasit.kalayci.shoppinglist.viewmodel.ItemViewModel
import java.io.ByteArrayOutputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemScreen(viewModel: ItemViewModel, navController: NavController) {
    var itemName by remember { mutableStateOf("") }
    var storeName by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var selectedImageBytes by remember { mutableStateOf<ByteArray?>(null) }
    var selectedImageBitmap by remember { mutableStateOf<Bitmap?>(null) }

    val context = LocalContext.current

    // Galeriden resim seçme launcher'ı
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            val inputStream = context.contentResolver.openInputStream(it)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            selectedImageBitmap = bitmap
            
            // Bitmap'i ByteArray'e çevir (Veritabanı için)
            val outputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            selectedImageBytes = outputStream.toByteArray()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Yeni Ürün Ekle") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Geri")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Resim Seçme Alanı
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant, MaterialTheme.shapes.medium)
                    .clickable { galleryLauncher.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {
                if (selectedImageBitmap != null) {
                    Image(
                        bitmap = selectedImageBitmap!!.asImageBitmap(),
                        contentDescription = "Seçilen Resim",
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Text("Resim Seç", style = MaterialTheme.typography.bodyMedium)
                }
            }

            OutlinedTextField(
                value = itemName,
                onValueChange = { itemName = it },
                label = { Text("Ürün Adı") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = storeName,
                onValueChange = { storeName = it },
                label = { Text("Mağaza Adı") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                label = { Text("Fiyat") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    if (itemName.isNotBlank()) {
                        val newItem = Item(itemName, storeName, price, selectedImageBytes)
                        viewModel.saveItem(newItem)
                        navController.popBackStack() // Kaydettikten sonra geri dön
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = itemName.isNotBlank()
            ) {
                Text("Kaydet")
            }
        }
    }
}
