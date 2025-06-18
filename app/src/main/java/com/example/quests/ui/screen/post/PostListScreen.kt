package com.example.quests.ui.screen.post

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.quests.domain.model.PostListUi
import com.example.quests.ui.components.CommonTopBar
import com.example.quests.ui.navigation.ScreenRoutes
import com.example.quests.ui.theme.QuestsTheme


@Composable
fun PostListScreen(
    navController: NavHostController, viewModel: PostListViewModel = hiltViewModel()
) {
    val state = viewModel.state
    PostListView(
        state = state, navToDetail = { postId ->
            navController.navigate(
                ScreenRoutes.PostDetailRoute.createRoute(postId.toString())
            )
        })
}


@Composable
fun PostListView(
    state: PostUiState, navToDetail: (Int) -> Unit
) {

    Scaffold(
        topBar = {
            CommonTopBar(
                title = "Posts",
                onBackClick = null)
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues), contentAlignment = Alignment.Center
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
                        items(state.post.size) { post ->
                            PostItem(state.post[post], navToDetail = navToDetail)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PostItem(
    post: PostListUi,
    navToDetail: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .fillMaxWidth()
            .clickable(enabled = post.id != null) {
                post.id?.let(navToDetail)
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = post.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = post.body,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "User ID: ${post.userId ?: "-"}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
                Text(
                    text = "Post ID: ${post.id ?: "-"}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun PostListViewPreview() {
    val sampleProducts = List(10) { index ->
        PostListUi(
            userId = index + 1, id = index + 1, title = "Sample Product $index", body = "This is a sample post description for post $index."
        )
    }

    QuestsTheme {
        PostListView(
            state = PostUiState(
                post = sampleProducts, isLoading = false, error = null
            )
        ) {}
    }

}

@Preview(showBackground = true)
@Composable
fun PostItemPreview() {
    val sampleProduct = PostListUi(
        userId = 1, id = 1, title = "Sample Product", body = "This is a sample post description."
    )
    QuestsTheme {
        PostItem(sampleProduct) {}
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
        ) {}
    }
}
