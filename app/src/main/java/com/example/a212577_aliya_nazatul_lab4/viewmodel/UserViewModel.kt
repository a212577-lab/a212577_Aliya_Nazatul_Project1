package com.example.a212577_aliya_nazatul_lab4.viewmodel

import androidx.lifecycle.ViewModel
import com.example.a212577_aliya_nazatul_lab4.model.BmiRecord
import com.example.a212577_aliya_nazatul_lab4.model.UserInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

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

    fun updateUser(name: String, date: String) {
        _userInfo.value = UserInfo(name, date)
    }

    fun addBmiRecord(bmi: String, category: String, date: String) {
        val newRecord = BmiRecord(bmi, category, date)

        _bmiHistory.value = listOf(newRecord) + _bmiHistory.value
    }
}