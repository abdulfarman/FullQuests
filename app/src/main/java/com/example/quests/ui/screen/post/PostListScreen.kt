package com.example.quests.ui.screen.post

import androidx.compose.foundation.clickable
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
import androidx.navigation.NavHostController
import com.example.quests.domain.model.PostListUi
import com.example.quests.ui.navigation.ScreenRoutes
import com.example.quests.ui.theme.QuestsTheme


@Composable
fun PostListScreen(
    navController: NavHostController,
    viewModel: PostListViewModel = hiltViewModel(), modifier: Modifier = Modifier
) {
    val state = viewModel.state
    PostListView(
        state = state, modifier = modifier,
        navToDetail= { postId ->
            navController.navigate(
                ScreenRoutes.PostDetailRoute.createRoute(postId.toString())
            )
        }
    )
}


@Composable
fun PostListView(state: PostUiState, modifier: Modifier = Modifier,
                 navToDetail: (Int) -> Unit
) {
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
                    items(state.post.size) { product ->
                        PostItem(state.post[product], navToDetail = navToDetail)
                    }
                }
            }
        }
    }
}

@Composable
fun PostItem(
    product: PostListUi,
    navToDetail: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)

            .fillMaxWidth()
            .clickable(true) {
                navToDetail(product.id ?: 0)
            }, elevation = CardDefaults.cardElevation()
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
fun PostListViewPreview() {
    val sampleProducts = List(10) { index ->
        PostListUi(
            userId = index + 1, id = index + 1, title = "Sample Product $index", body = "This is a sample product description for product $index."
        )
    }

    QuestsTheme {
        PostListView(
            state = PostUiState(
                post = sampleProducts, isLoading = false, error = null
            )
        ){}
    }

}

@Preview(showBackground = true)
@Composable
fun PostItemPreview() {
    val sampleProduct = PostListUi(
        userId = 1, id = 1, title = "Sample Product", body = "This is a sample product description."
    )
    QuestsTheme {
        PostItem(sampleProduct){}
    }
}

@Preview(showBackground = true)
@Composable
fun PostListViewErrorPreview() {
    QuestsTheme {
        PostListView(
            state = PostUiState(
                post = emptyList(), isLoading = false, error = "Failed to load posts"
            )
        ){}
    }
}
