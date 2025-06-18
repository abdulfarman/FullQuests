package com.example.quests

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.quests.ui.navigation.AppMainScreen
import com.example.quests.ui.theme.QuestsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint 
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuestsTheme {
                    AppMainScreen()
            }
        }
    }
}