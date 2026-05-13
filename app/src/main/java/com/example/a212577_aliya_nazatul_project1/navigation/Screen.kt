package com.example.a212577_aliya_nazatul_project1.navigation

//sealed --> only allow defined screens
sealed class Screen(val route: String) {
    object Calculator : Screen("calculator")
    object History : Screen("history")
    object Diary : Screen("diary")
    object Activity : Screen("activity")
}