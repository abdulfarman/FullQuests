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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.quests.domain.model.PostDetailUi
import com.example.quests.ui.theme.QuestsTheme


@Composable
fun PostDetailScreen(
    postId: Int, navController: NavHostController,
    viewModel: PostDetailViewModel = hiltViewModel(), modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {
        viewModel.loadPost(postId)
    }

    val state = viewModel.state
    PostDetailView(
        state = state, modifier = modifier
    )
}


@Composable
fun PostDetailView(state: PostDetailUiState, modifier: Modifier = Modifier) {
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
                PostDetailContent(post = state.post)
            }
        }
    }
}

@Composable
fun PostDetailContent(post: PostDetailUi?) {
    Column {
        Text(text = post?.title ?: "")
        Text(text = post?.body ?: "")
    }
}


@Preview(showBackground = true)
@Composable
fun PostDetailScreenViewPreview() {
    val sampleProduct =
        PostDetailUi(
            userId = 1, id = 1, title = "Sample Product ", body = "This is a sample product description for product."
        )


    QuestsTheme {
        PostDetailView(
            state = PostDetailUiState(
                post = sampleProduct, isLoading = false, error = null
            )
        )
    }

}


@Preview(showBackground = true)
@Composable
fun PostDetailViewErrorPreview() {
    QuestsTheme {
        PostDetailView(
            state = PostDetailUiState(
                post = null, isLoading = false, error = "Failed to load post"
            )
        )
    }
}
