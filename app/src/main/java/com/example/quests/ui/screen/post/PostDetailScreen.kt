package com.example.quests.ui.screen.post

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.quests.domain.model.PostDetailUi
import com.example.quests.ui.components.CommonTopBar
import com.example.quests.ui.theme.QuestsTheme


@Composable
fun PostDetailScreen(
    postId: Int, navController: NavHostController, viewModel: PostDetailViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.loadPost(postId)
    }

    val state = viewModel.state
    PostDetailView(
        state = state, navBack = {
            navController.popBackStack()
        })
}


@Composable
fun PostDetailView(state: PostDetailUiState, navBack: (() -> Unit)) {

    Scaffold(
        topBar = {
            CommonTopBar(
                title = "Post Detail",
                onBackClick = { navBack() },
            )

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
                    PostDetailContent(post = state.post)
                }
            }
        }

    }
}

@Composable
fun PostDetailContent(post: PostDetailUi?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (post == null) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            Text(
                text = "Loading...", modifier = Modifier.align(Alignment.CenterHorizontally), style = MaterialTheme.typography.bodyMedium
            )
        } else {
            Text(
                text = post.title, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = post.body, style = MaterialTheme.typography.bodyLarge, lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Divider()

            post.userId?.let {
                Text(
                    text = "Posted by user #$it", style = MaterialTheme.typography.bodySmall, color = Color.Gray, modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PostDetailScreenViewPreview() {
    val sampleProduct = PostDetailUi(
        userId = 1, id = 1, title = "Sample Product ", body = "This is a sample post description for post."
    )


    QuestsTheme {
        PostDetailView(
            state = PostDetailUiState(
                post = sampleProduct, isLoading = false, error = null
            ), navBack = {})
    }

}


@Preview(showBackground = true)
@Composable
fun PostDetailViewErrorPreview() {
    QuestsTheme {
        PostDetailView(
            state = PostDetailUiState(
                post = null, isLoading = false, error = "Failed to load post"
            ), navBack = {})
    }
}
