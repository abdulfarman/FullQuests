package com.example.quests

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.quests.ui.screen.post.PostDetailScreen
import com.example.quests.ui.screen.post.PostListScreen
import com.example.quests.ui.theme.QuestsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint 
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuestsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PostDetailScreen(id = 1 ,modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}