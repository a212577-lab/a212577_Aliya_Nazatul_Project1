package com.example.a212577_aliya_nazatul_lab4.navigation

sealed class Screen(val route: String) {
    object Calculator : Screen("calculator")
    object History : Screen("history")
    object Diary : Screen("diary")
}