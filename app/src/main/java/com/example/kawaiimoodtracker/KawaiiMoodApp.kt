package com.example.kawaiimoodtracker

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun KawaiiMoodApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "CurrentMoodScreen"
    ) {
        composable("CurrentMoodScreen") { CurrentMoodScreen(navController)}
        composable("MoodCalendarScreen"){ MoodCalendarScreen(navController)}
        composable("AddMoodScreen"){ AddMoodScreen(navController)}
        composable("PreviousMoodScreen"){ PreviousMoodScreen(navController)}
    }
}

