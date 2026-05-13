package com.example.a212577_aliya_nazatul_project1.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.a212577_aliya_nazatul_project1.model.BmiRecord
import com.example.a212577_aliya_nazatul_project1.model.UserInfo
import com.example.a212577_aliya_nazatul_project1.model.ActivityItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

//viewmodel --> handle data (userInfo & bmiHistory)
//stateflow --> data holder (update when value changes)
class UserViewModel : ViewModel() {

    private val _userInfo = MutableStateFlow(
        UserInfo(
            name = "Aliya",
            date = "21 Apr 2026"
        )
    )

    val userInfo: StateFlow<UserInfo> = _userInfo

    private val _bmiHistory = MutableStateFlow<List<BmiRecord>>(emptyList())
    val bmiHistory: StateFlow<List<BmiRecord>> = _bmiHistory

    private val _activities = mutableStateListOf<ActivityItem>()
    val activities: List<ActivityItem> = _activities

    fun updateUser(name: String, date: String) {
        _userInfo.value = UserInfo(name, date)
    }

    fun addBmiRecord(bmi: String, category: String, date: String) {
        val newRecord = BmiRecord(bmi, category, date)

        _bmiHistory.value = listOf(newRecord) + _bmiHistory.value
    }

    fun addActivity(name: String) {
        _activities.add(ActivityItem(name,
            calories = 150,
            duration = 30))
    }

}