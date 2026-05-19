package com.example.a212577_aliya_nazatul_lab5.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.a212577_aliya_nazatul_lab5.model.BmiRecord
import com.example.a212577_aliya_nazatul_lab5.model.UserInfo
import com.example.a212577_aliya_nazatul_lab5.model.ActivityItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import com.example.a212577_aliya_nazatul_lab5.data.ActivityDao
import com.example.a212577_aliya_nazatul_lab5.data.ActivityEntity
import com.example.a212577_aliya_nazatul_lab5.data.ActivityRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UserViewModel(private val repository: ActivityRepository) : ViewModel() {

    private val _userInfo = MutableStateFlow(
        UserInfo(
            name = "Aliya",
            date = "13 May 2026"
        )
    )
    val userInfo: StateFlow<UserInfo> = _userInfo

    fun updateUser(name: String, date: String) {
        _userInfo.value = UserInfo(name, date)
    }
    private val _bmiHistory = MutableStateFlow<List<BmiRecord>>(emptyList())

    val bmiHistory: StateFlow<List<BmiRecord>> = _bmiHistory

    fun addBmiRecord(bmi: String, category: String, date: String) {
        val newRecord = BmiRecord(bmi, category, date)

        _bmiHistory.value = listOf(newRecord) + _bmiHistory.value
    }

    private val _activities = mutableStateListOf<ActivityItem>()

    val activities = repository.allActivities

    fun addActivity(name: String) {
        viewModelScope.launch {
            repository.insert(
                ActivityEntity(name = name, calories = 100)
            )
        }
    }

    fun deleteActivity(activity: ActivityEntity) {
        viewModelScope.launch {
            repository.delete(activity)
        }
    }
}

