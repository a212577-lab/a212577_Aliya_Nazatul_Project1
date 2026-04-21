package com.example.a212577_aliya_nazatul_lab4.screen

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
import androidx.compose.material3.CircularProgressIndicator
import com.example.a212577_aliya_nazatul_lab4.R
import com.example.a212577_aliya_nazatul_lab4.viewmodel.UserViewModel


@Composable
fun DiaryScreen(userViewModel: UserViewModel){
    val userInfo by userViewModel.userInfo.collectAsState()
    var waterCount by remember { mutableStateOf(0) }
    var weight by remember { mutableStateOf(50f) }
    var previousWeight by remember { mutableStateOf(48f) }

    Column(modifier = Modifier.padding(20.dp)) {

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

                // LEFT SIDE (ICON + TEXT)
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

                // RIGHT SIDE (CIRCLE PROGRESS)
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
                // LEFT SIDE (ICON + TEXT)
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

                // LEFT SIDE (ICON + TEXT)
                Column {

                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Image(
                            painter = painterResource(R.drawable.weight_image),
                            contentDescription = "Water",
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

                // RIGHT SIDE (CIRCLE PROGRESS)
                Button(onClick = {
                    previousWeight = weight
                }) {
                    Text("Update", style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}
