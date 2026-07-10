package com.example.nearbuymarketplace.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable
import com.example.nearbuymarketplace.viewmodel.MarketplaceViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SellScreen(
    viewModel: MarketplaceViewModel,
    onNavigateBack: () -> Unit
) {
    var itemTitle by remember { mutableStateOf("") }
    var itemPrice by remember { mutableStateOf("") }
    var itemDescription by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("List an Item", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { onNavigateBack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Go Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = itemTitle,
                onValueChange = { itemTitle = it },
                label = { Text("What are you selling?") },
                placeholder = { Text("e.g., Mountain Bike, Textbook") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = itemPrice,
                onValueChange = { itemPrice = it },
                label = { Text("Price") },
                prefix = { Text("KSh ", fontWeight = FontWeight.Bold) },
                placeholder = { Text("0") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = itemDescription,
                onValueChange = { itemDescription = it },
                label = { Text("Description") },
                placeholder = { Text("Describe the condition, size, etc.") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                maxLines = 4
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    if (itemTitle.isNotEmpty() && itemPrice.isNotEmpty()) {

                        viewModel.addItem(
                            title = itemTitle,
                            price = itemPrice.toDoubleOrNull() ?: 0.0,
                            desc = itemDescription
                        )
                        
                        onNavigateBack()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text("Post Item to Map", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SellScreenPreview() {
    com.example.nearbuymarketplace.ui.theme.NearBuyMarketplaceTheme {
        SellScreen(
            viewModel = MarketplaceViewModel(),
            onNavigateBack = {}
        )
    }
}