package com.example.quests.ui.screen.post

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quests.domain.model.PostListUi
import com.example.quests.ui.theme.QuestsTheme


@Composable
fun PostDetailScreen(viewModel: PostListViewModel = hiltViewModel(), modifier: Modifier) {
    val state = viewModel.state
    PostDetailView(
        state = state, modifier = modifier
    )
}


@Composable
fun PostDetailView(state: PostUiState, modifier: Modifier = Modifier) {
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
                //Todo
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PostDetailScreenViewPreview() {
    val sampleProducts = List(10) { index ->
        PostListUi(
            userId = index + 1, id = index + 1, title = "Sample Product $index", body = "This is a sample product description for product $index."
        )
    }

    QuestsTheme {
        PostDetailView(
            state = PostUiState(
                products = sampleProducts, isLoading = false, error = null
            )
        )
    }

}


@Preview(showBackground = true)
@Composable
fun PostDetailViewErrorPreview() {
    QuestsTheme {
        PostDetailView(
            state = PostUiState(
                products = emptyList(), isLoading = false, error = "Failed to load post"
            )
        )
    }
}
