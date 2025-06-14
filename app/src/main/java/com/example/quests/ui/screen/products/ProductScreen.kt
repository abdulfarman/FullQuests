package com.example.quests.ui.screen.products

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quests.domain.model.ProductUi


@Composable
fun ProductScreen(viewModel: ProductViewModel = hiltViewModel(), modifier: Modifier) {
    val state = viewModel.state
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isLoading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            state.error != null -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Error: ${state.error}")
                }
            }

            else -> {
                LazyColumn {
                    items(state.products.size) { product ->
                        ProductItem(state.products[product])
                    }
                }
            }
        }
    }
}

@Composable
fun ProductItem(product: ProductUi) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation()
    ) {
        Row(Modifier.padding(16.dp)) {
            Column {
                Text(product.title, fontWeight = FontWeight.Bold)
                Text("₹${product.description}", color = Color.Gray)
            }
        }
    }
}

