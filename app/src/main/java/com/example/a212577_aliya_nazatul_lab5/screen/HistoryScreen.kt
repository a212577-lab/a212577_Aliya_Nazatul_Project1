package com.example.a212577_aliya_nazatul_lab5.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.collectAsState
import com.example.a212577_aliya_nazatul_lab5.viewmodel.UserViewModel

@Composable
fun HistoryScreen(userViewModel: UserViewModel) {

    val history = userViewModel.bmiHistory.collectAsState().value

    Column(modifier = Modifier.verticalScroll(rememberScrollState()).padding(20.dp)) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                "BMI History",
                style = MaterialTheme.typography.displayMedium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        history.forEach { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Text(
                    "${item.bmi} - ${item.category} (${item.date})",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}
