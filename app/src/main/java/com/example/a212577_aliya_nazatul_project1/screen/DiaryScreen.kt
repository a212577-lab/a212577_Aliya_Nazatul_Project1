package com.example.a212577_aliya_nazatul_project1.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.rememberScrollState
import androidx.navigation.NavController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import com.example.a212577_aliya_nazatul_project1.R
import com.example.a212577_aliya_nazatul_project1.viewmodel.UserViewModel

@Composable
fun DiaryScreen(navController : NavController, userViewModel: UserViewModel){

    val userInfo by userViewModel.userInfo.collectAsState()

    var waterCount by remember { mutableStateOf(0) }
    var weight by remember { mutableStateOf(50f) }
    var previousWeight by remember { mutableStateOf(48f) }
    var showDialog by remember { mutableStateOf(false) }
    var weightInput by remember { mutableStateOf("") }
    val activities = userViewModel.activities

    Column(modifier = Modifier.verticalScroll(rememberScrollState()).padding(20.dp)) {

        Text("Daily Diary", style = MaterialTheme.typography.displayMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Text("Name: ${userInfo.name}")
        Text("Date: ${userInfo.date}")

        Spacer(modifier = Modifier.height(16.dp))

        // WATER CARD
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable {
                    if (waterCount < 8) waterCount++
                }
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column {

                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Image(
                            painter = painterResource(R.drawable.cup),
                            contentDescription = "Water",
                            modifier = Modifier.size(24.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            "Water",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text("$waterCount / 8 cups")
                }

                CircularProgressIndicator(
                progress = { waterCount / 8f },
                modifier = Modifier,
                color = MaterialTheme.colorScheme.primary,
                strokeWidth = ProgressIndicatorDefaults.CircularStrokeWidth,
                trackColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.2f),
                strokeCap = ProgressIndicatorDefaults.CircularDeterminateStrokeCap,
                )
            }
        }

        // STEPS CARD
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val steps = 4000
                val goal = 7000

                Column {

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(R.drawable.footprint),
                            contentDescription = "Steps",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Steps", style = MaterialTheme.typography.titleMedium)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("$steps / $goal steps")
                }
                CircularProgressIndicator(
                progress = { steps / goal.toFloat() },
                modifier = Modifier,
                color = MaterialTheme.colorScheme.primary,
                strokeWidth = ProgressIndicatorDefaults.CircularStrokeWidth,
                trackColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.2f),
                strokeCap = ProgressIndicatorDefaults.CircularDeterminateStrokeCap,
                )
            }
        }
        // WEIGHT CARD
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column {

                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Image(
                            painter = painterResource(R.drawable.weight_image),
                            contentDescription = "weight",
                            modifier = Modifier.size(24.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            "Weight Tracker",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text("Current: $weight kg")
                    Text("Previous: $previousWeight kg")
                }

                Button(
                    onClick = {
                        showDialog = true
                    }
                ) {
                    Text("Update")
                }

            }
        }
        // ACTIVITY CARD
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable {
                    navController.navigate("activity")
                }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.exercise),
                    contentDescription = "Activity",
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Activity",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
        Text(
            text = "Today's Activities",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(8.dp)
        )

        activities.forEach { activity ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = activity.name,
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text("${activity.calories} kcal")
                }
            }
        }
    }
    if (showDialog) {

        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },

            title = {
                Text("Update Weight")
            },

            text = {

                OutlinedTextField(
                    value = weightInput,
                    onValueChange = {
                        weightInput = it
                    },
                    label = {
                        Text("Enter weight (kg)")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    singleLine = true
                )
            },

            confirmButton = {

                Button(
                    onClick = {

                        previousWeight = weight

                        weightInput.toFloatOrNull()?.let {
                            weight = it
                        }

                        showDialog = false
                        weightInput = ""
                    }
                ) {
                    Text("Save")
                }
            },

            dismissButton = {

                TextButton(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}
