package com.example.quests.ui.navigation

sealed class ScreenRoutes(val route: String) {
    object PostListRoute : ScreenRoutes("post_list_screen")
    object PostDetailRoute : ScreenRoutes("post_detail_screen/{postId}") {
        fun createRoute(postId: String): String {
            return "post_detail_screen/$postId"
        }
    }
}