package com.example.a212577_aliya_nazatul_lab5.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.a212577_aliya_nazatul_lab5.R
import androidx.navigation.compose.rememberNavController
import com.example.a212577_aliya_nazatul_lab5.BMIApp
import com.example.a212577_aliya_nazatul_lab5.data.ActivityRepository
import com.example.a212577_aliya_nazatul_lab5.data.AppDatabase
import com.example.a212577_aliya_nazatul_lab5.navigation.Screen
import com.example.a212577_aliya_nazatul_lab5.ui.theme.A212577_Aliya_Nazatul_Lab5Theme
import com.example.a212577_aliya_nazatul_lab5.viewmodel.UserViewModel
import com.example.a212577_aliya_nazatul_lab5.viewmodel.UserViewModelFactory

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context)
    val dao = db.activityDao()
    val repository = ActivityRepository(dao)
    val userViewModel: UserViewModel = viewModel(
        factory = UserViewModelFactory(repository)
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(Screen.Diary.route) },
                    label = { Text("Diary") },
                    icon = {
                        Image(
                            painter = painterResource(R.drawable.book),
                            contentDescription = "Diary",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(Screen.Calculator.route) },
                    label = { Text("Calculator") },
                    icon = {
                        Image(
                            painter = painterResource(R.drawable.calculator),
                            contentDescription = "Calculator",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(Screen.History.route) },
                    label = { Text("History") },
                    icon = {
                        Image(
                            painter = painterResource(R.drawable.history),
                            contentDescription = "History",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                )
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Calculator.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(Screen.Calculator.route) {
                BMIApp(navController, userViewModel)
            }
            composable(Screen.History.route) {
                HistoryScreen(userViewModel)
            }
            composable(Screen.Diary.route) {
                DiaryScreen(navController, userViewModel)
            }
            composable("activity") {
                ActivityScreen(navController, userViewModel)
            }
            composable("weight_progress") {
                WeightProgressScreen(navController, userViewModel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    A212577_Aliya_Nazatul_Lab5Theme {
        MainScreen()
    }
}
