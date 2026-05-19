package com.example.a212577_aliya_nazatul_lab5.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.a212577_aliya_nazatul_lab5.viewmodel.UserViewModel

@Composable
fun ActivityScreen(navController: NavController, userViewModel: UserViewModel) {

    val activities = listOf(
        "Running", "Walking", "Cycling",
        "Swimming", "Hiking", "Home Workout",
        "Gym", "Others"
    )

    var selectedActivity by remember { mutableStateOf<String?>(null) }


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text(
                text = "Log Activity",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        items(activities) { activity ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable {
                        selectedActivity = activity
                    }
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = activity)

                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add"
                    )
                }
            }
        }
    }

    // AlertDialog is placed outside the scrollable area.
    if (selectedActivity != null) {
        AlertDialog(
            onDismissRequest = { selectedActivity = null },
            title = { Text("Add Activity") },
            text = { Text("Add ${selectedActivity} to your diary?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        userViewModel.addActivity(selectedActivity!!)
                        selectedActivity = null
                        navController.popBackStack() // go back
                    }
                ) {
                    Text("Add Activity")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { selectedActivity = null }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}
