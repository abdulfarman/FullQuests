package com.example.quests.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.quests.ui.screen.post.PostDetailScreen
import com.example.quests.ui.screen.post.PostListScreen

@Composable
fun AppNavigation(innerPadding: PaddingValues) {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.PostListRoute.route
    ) {
        composable(route = ScreenRoutes.PostListRoute.route) {
            PostListScreen(navController = navController)
        }

        composable(
            route = ScreenRoutes.PostDetailRoute.route,
            arguments = listOf(
                navArgument("postId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val postId = backStackEntry.arguments?.getString("postId")
            PostDetailScreen(navController = navController, postId = postId?.toIntOrNull() ?: 0)
        }
    }
}