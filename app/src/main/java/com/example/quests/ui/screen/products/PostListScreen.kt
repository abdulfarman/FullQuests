package com.example.quests.ui.screen.products

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quests.domain.model.PostListUi
import com.example.quests.ui.theme.QuestsTheme


@Composable
fun ProductScreen(viewModel: PostListViewModel = hiltViewModel(), modifier: Modifier) {
    val state = viewModel.state
    ProductScreenView(
        state = state, modifier = modifier
    )
}


@Composable
fun ProductScreenView(state: ProductUiState, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier, contentAlignment = Alignment.Center
    ) {
        when {
            state.isLoading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            state.error != null -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info, contentDescription = "Error", tint = Color.Gray
                        )
                        Text(state.error)
                    }
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
fun ProductItem(product: PostListUi) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(), elevation = CardDefaults.cardElevation()
    ) {
        Row(Modifier.padding(16.dp)) {
            Column {
                Text(product.title, fontWeight = FontWeight.Bold)
                Text("₹${product.body}", color = Color.Gray)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductScreenViewPreview() {
    val sampleProducts = List(10) { index ->
        PostListUi(
            userId = index + 1, id = index + 1, title = "Sample Product $index", body = "This is a sample product description for product $index."
        )
    }

    QuestsTheme {
        ProductScreenView(
            state = ProductUiState(
                products = sampleProducts, isLoading = false, error = null
            )
        )
    }

}

@Preview(showBackground = true)
@Composable
fun ProductItemPreview() {
    val sampleProduct = PostListUi(
        userId = 1, id = 1, title = "Sample Product", body = "This is a sample product description."
    )
    QuestsTheme {
        ProductItem(sampleProduct)
    }
}

@Preview(showBackground = true)
@Composable
fun ProductItemErrorPreview() {
    QuestsTheme {
        ProductScreenView(
            state = ProductUiState(
                products = emptyList(), isLoading = false, error = "Failed to load posts"
            )
        )
    }
}
