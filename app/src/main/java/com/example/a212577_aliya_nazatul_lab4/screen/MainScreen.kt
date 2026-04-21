package com.example.a212577_aliya_nazatul_lab4.screen

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.a212577_aliya_nazatul_lab4.R
import com.example.a212577_aliya_nazatul_lab4.BMIApp
import com.example.a212577_aliya_nazatul_lab4.navigation.Screen
import com.example.a212577_aliya_nazatul_lab4.ui.theme.A212577_Aliya_Nazatul_Lab4Theme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a212577_aliya_nazatul_lab4.viewmodel.UserViewModel
@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val userViewModel: UserViewModel = viewModel()

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
                    selected = true,
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
                BMIApp(userViewModel)
            }

            composable(Screen.History.route) {
                HistoryScreen(userViewModel)
            }

            composable(Screen.Diary.route) {
                DiaryScreen(userViewModel)
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    A212577_Aliya_Nazatul_Lab4Theme {
        MainScreen()
    }
}