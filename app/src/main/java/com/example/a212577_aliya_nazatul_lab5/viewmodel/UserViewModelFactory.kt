package com.example.a212577_aliya_nazatul_lab5.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a212577_aliya_nazatul_lab5.data.ActivityRepository

class UserViewModelFactory(
    private val repository: ActivityRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(repository) as T
    }
}