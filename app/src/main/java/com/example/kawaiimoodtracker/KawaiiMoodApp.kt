package com.example.kawaiimoodtracker

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun KawaiiMoodApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val moodViewModel: MoodViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = "AddMoodScreen"
    ) {
        composable("CurrentMoodScreen") { CurrentMoodScreen(navController, moodViewModel = moodViewModel)}
        composable("MoodCalendarScreen"){ MoodCalendarScreen(navController, moodViewModel = moodViewModel)}
        composable("AddMoodScreen"){ AddMoodScreen(navController, moodViewModel = moodViewModel)}
        composable("PreviousMoodScreen"){ PreviousMoodScreen(navController, moodViewModel = moodViewModel)}
    }
}

