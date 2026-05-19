package com.example.a212577_aliya_nazatul_lab5.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.a212577_aliya_nazatul_lab5.R
import com.example.a212577_aliya_nazatul_lab5.viewmodel.UserViewModel

@Composable
fun WeightProgressScreen(navController: NavController, userViewModel: UserViewModel) {

    val weights = listOf(
        53.8f, 53.5f, 53.0f, 53.4f,
        53.0f, 52.6f, 52.2f
    )
    val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")

    val minWeight = weights.minOrNull() ?: 0f
    val maxWeight = weights.maxOrNull() ?: 1f
    val range = maxWeight - minWeight

    var startAnimation by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        startAnimation = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        Text(
            text = "Weight Progress",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            weights.forEachIndexed { index, weight ->

                val normalized = if (range == 0f) {
                    1f
                } else {
                    (weight - minWeight) / range
                }

                val targetHeight = (normalized * 160f + 20f)

                val animatedHeight by animateFloatAsState(
                    targetValue = if (startAnimation) targetHeight else 0f,
                    animationSpec = tween(
                        durationMillis = 800,
                        delayMillis = index * 100
                    ),
                    label = "barAnimation"
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Box(
                        modifier = Modifier
                            .width(20.dp)
                            .height(animatedHeight.dp)
                            .background(
                                color = colorScheme.tertiary,
                                shape = RoundedCornerShape(6.dp)
                            )
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = days[index],
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(80.dp))

        Text(
            text = "You are doing great! Keep going!",
            color = colorScheme.onBackground,
            style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(R.drawable.finish),
            contentDescription = "Motivation Image",

            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),

            contentScale = ContentScale.Fit
        )
    }
}